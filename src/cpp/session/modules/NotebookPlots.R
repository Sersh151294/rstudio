#
# NotebookPlots.R
#
# Copyright (C) 2022 by Posit Software, PBC
#
# Unless you have received this program directly from Posit Software pursuant
# to the terms of a commercial license agreement with Posit Software, then
# this program is licensed to you under the terms of version 3 of the
# GNU Affero General Public License. This program is distributed WITHOUT
# ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
# MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
# AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
#
#

# creates the notebook graphics device 
.rs.addFunction("createNotebookGraphicsDevice", function(filename,
                                                         height,
                                                         width,
                                                         units,
                                                         pixelRatio,
                                                         extraArgs)
{
   if (units == "px") # px = automatic size behavior 
   {
    height <- height * pixelRatio
    width <- width * pixelRatio
   }

   # form the arguments to the graphics device creator
   args <- list(
      filename = filename,
      width    = width,
      height   = height, 
      units    = units,
      res      = 96 * pixelRatio
   )

   if (nchar(extraArgs) > 0)
   {
      # trim leading comma from extra args if present
      if (identical(substr(extraArgs, 1, 1), ","))
         extraArgs <- substring(extraArgs, 2)
      
      # parse extra args and merge with existing args
      extraList <- NULL
      tryCatch({
         extraList <- eval(parse(text = paste("list(", extraArgs, ")")))
      }, error = function(e) {
         # failure to parse implies we won't merge below
      })
      
      if (is.list(extraList))
         args <- c(args, extraList)
   }

   gdBackend <- getOption("RStudioGD.backend")
   if (!identical(gdBackend, "default"))
   {
     # pass along graphics device backend if set
     # we allow this option to be temporarily set by the knitr chunk option while the notebook
     # chunk is executing; this takes precedence over the device passed via extraArgs
     args$type <- gdBackend
   }
   
   # if it looks like we're using AGG, delegate to that
   if (identical(args$type, "ragg"))
   {
      device <- ragg::agg_png(
         filename = filename,
         width    = width,
         height   = height,
         units    = units,
         res      = 96 * pixelRatio
      )
      
      return(device)
   }
   
   # create the device
   require(grDevices, quietly = TRUE)
   do.call(what = png, args = args)
})

# this seems like it is the only thing manipulated from NotebookPlots.cpp side
# this is where pixelRatio is introduced to the machinery
# it likely originates in RClientMetrics.cpp where it is drawn from
# "r.session.client_metrics.device-pixel-ratio"
.rs.addFunction("setNotebookGraphicsOption", function(filename,
                                                      height,
                                                      width,
                                                      units,
                                                      pixelRatio,
                                                      extraArgs)
{
   options(device = function()
   {
      .rs.createNotebookGraphicsDevice(filename, height, width, units,  pixelRatio, extraArgs)
      dev.control(displaylist = "enable")
      # this introduces margins that makes the figure different from the actual output
      # as seen in the rendered document, so disable it
      # .rs.setNotebookMargins()
   })
})

.rs.addFunction("saveNotebookGraphics", function(plot, filename)
{
   save(plot, file = filename)
})

# this should not be used in my opinion (it is never called with
# the change of setNotebookGraphicsOption above)
.rs.addFunction("setNotebookMargins", function() {
   #           bot  left top  right
   par(mar = c(5.1, 4.1, 2.1, 2.1))
})

.rs.addFunction("replayNotebookPlots", function(width, height, pixelRatio, tempFile, extraArgs) {
   
   require(grDevices, quietly = TRUE)
   
   loadedPackages <- Sys.getenv("RS_LOADED_PACKAGES", unset = "")
   loadedPackages <- strsplit(loadedPackages, ",", fixed = TRUE)[[1L]]
   
   # TODO: It'd be really nice if we could somehow infer these from
   # the grobs stored within the plot object.
   # https://github.com/rstudio/rstudio/issues/4330
   commonPackages <- c("ggplot2", "ggrepel", "ggforce")
   suppressPackageStartupMessages({
      for (package in commonPackages)
         if (package %in% loadedPackages)
            require(package, character.only = TRUE, quietly = TRUE)
   })
   
   # open stdin (for consuming snapshots from parent process)
   stdin <- file("stdin")
   snapshots <- readLines(stdin, warn = FALSE)
   
   lapply(snapshots, function(snapshot) {
      
      # ignore empty lines
      if (nchar(tools::file_ext(snapshot)) < 1)
         return(invisible(NULL))
      
      # create the PNG device on which we'll regenerate the plot -- it's somewhat
      # wasteful to use a separate device per plot, but the alternative is
      # doing a lot of state management and post-processing of the files
      # output from the device
      output <- paste(
         tools::file_path_sans_ext(snapshot),
         "resized.png",
         sep = "."
      )
      
      height <- if (height <= 0) width / 1.618 else height
      
      .rs.createNotebookGraphicsDevice(output, height, width, 
                                       "px", pixelRatio, extraArgs)
      
      # actually replay the plot onto the device
      tryCatch(
         .rs.restoreGraphics(snapshot),
         error = function(err) {
            writeLines(conditionMessage(err), con = stderr())
         }
      )
      
      # close the device; has the side effect of committing the plot to disk
      dev.off()
      
      # if the plot file was written out, emit to standard out for caller
      if (!file.exists(output))
         return(invisible(NULL))
      
      # build output path
      final <- paste(tools::file_path_sans_ext(snapshot), "png", sep = ".")
      if (tempFile) {
         
         chunksPath <- dirname(snapshot)
         chunksTempPath <- file.path(chunksPath, "temp")
         
         if (!dir.exists(chunksTempPath))
            dir.create(chunksTempPath)
         
         chunkBaseName <- basename(tools::file_path_sans_ext(snapshot))
         
         final <- file.path(
            chunksTempPath,
            paste(chunkBaseName, "png", sep = ".")
         )
      }
         
      # remove the old copy of the plot if it existed
      file.copy(output, final, overwrite = TRUE)
      unlink(output)
         
      if (file.exists(final))
         writeLines(final)
      
   })
   
   invisible(NULL)
})
