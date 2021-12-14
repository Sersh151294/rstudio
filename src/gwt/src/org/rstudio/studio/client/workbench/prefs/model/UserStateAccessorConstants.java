/* UserStateAccessorConstants.java
 *
 * Copyright (C) 2021 by RStudio, PBC
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */

/* DO NOT HAND-EDIT! This file is automatically generated from the formal user preference schema
 * JSON. To add a preference, add it to "user-prefs-schema.json", then run "generate-prefs.R" to
 * rebuild this file.
 */

package org.rstudio.studio.client.workbench.prefs.model;
import com.google.gwt.i18n.client.Constants;

public interface UserStateAccessorConstants extends Constants {

   /**
    * A unique identifier representing the user and machine.
    */
   @DefaultStringValue("")
   String contextIdTitle();
   @DefaultStringValue("A unique identifier representing the user and machine.")
   String contextIdDescription();

   /**
    * Whether we have automatically created an .Rprofile for this user.
    */
   @DefaultStringValue("")
   String autoCreatedProfileTitle();
   @DefaultStringValue("Whether we have automatically created an .Rprofile for this user.")
   String autoCreatedProfileDescription();

   /**
    * The color theme to apply.
    */
   @DefaultStringValue("")
   String themeTitle();
   @DefaultStringValue("The color theme to apply.")
   String themeDescription();

   /**
    * The directory path under which to place new projects by default. Shadows a uipref.
    */
   @DefaultStringValue("")
   String defaultProjectLocationTitle();
   @DefaultStringValue("The directory path under which to place new projects by default. Shadows a uipref.")
   String defaultProjectLocationDescription();

   /**
    * Whether to clear hidden objects along with visible objects when clearing the workspace. Set automatically to remember last action.
    */
   @DefaultStringValue("")
   String clearHiddenTitle();
   @DefaultStringValue("Whether to clear hidden objects along with visible objects when clearing the workspace. Set automatically to remember last action.")
   String clearHiddenDescription();

   /**
    * The most recently used plot export options.
    */
   @DefaultStringValue("")
   String exportPlotOptionsTitle();
   @DefaultStringValue("The most recently used plot export options.")
   String exportPlotOptionsDescription();

   /**
    * The most recently used viewer export options.
    */
   @DefaultStringValue("")
   String exportViewerOptionsTitle();
   @DefaultStringValue("The most recently used viewer export options.")
   String exportViewerOptionsDescription();

   /**
    * The most recently used options for saving a plot as a PDF.
    */
   @DefaultStringValue("")
   String savePlotAsPdfOptionsTitle();
   @DefaultStringValue("The most recently used options for saving a plot as a PDF.")
   String savePlotAsPdfOptionsDescription();

   /**
    * Most recently used settings for compiling a notebook from an R script.
    */
   @DefaultStringValue("")
   String compileRNotebookPrefsTitle();
   @DefaultStringValue("Most recently used settings for compiling a notebook from an R script.")
   String compileRNotebookPrefsDescription();

   /**
    * Most recently used settings for compiling a notebook using R Markdown.
    */
   @DefaultStringValue("")
   String compileRMarkdownNotebookPrefsTitle();
   @DefaultStringValue("Most recently used settings for compiling a notebook using R Markdown.")
   String compileRMarkdownNotebookPrefsDescription();

   /**
    * Whether to show UI for publishing content.
    */
   @DefaultStringValue("")
   String showPublishUiTitle();
   @DefaultStringValue("Whether to show UI for publishing content.")
   String showPublishUiDescription();

   /**
    * Whether to show UI for publishing content to RStudio Connect.
    */
   @DefaultStringValue("")
   String enableRsconnectPublishUiTitle();
   @DefaultStringValue("Whether to show UI for publishing content to RStudio Connect.")
   String enableRsconnectPublishUiDescription();

   /**
    * The default (last) account used for publishing
    */
   @DefaultStringValue("")
   String publishAccountTitle();
   @DefaultStringValue("The default (last) account used for publishing")
   String publishAccountDescription();

   /**
    * The preferred width, in pixels, of the document outline pane.
    */
   @DefaultStringValue("")
   String documentOutlineWidthTitle();
   @DefaultStringValue("The preferred width, in pixels, of the document outline pane.")
   String documentOutlineWidthDescription();

   /**
    * How to create new connections to data sources.
    */
   @DefaultStringValue("")
   String connectViaTitle();
   @DefaultStringValue("How to create new connections to data sources.")
   String connectViaDescription();

   /**
    * The kind of handler to invoke when errors occur.
    */
   @DefaultStringValue("")
   String errorHandlerTypeTitle();
   @DefaultStringValue("The kind of handler to invoke when errors occur.")
   String errorHandlerTypeDescription();

   /**
    * Whether or not the MinGW compiler with GCC 4.9 is used.
    */
   @DefaultStringValue("")
   String usingMingwGcc49Title();
   @DefaultStringValue("Whether or not the MinGW compiler with GCC 4.9 is used.")
   String usingMingwGcc49Description();

   /**
    * Whether or not the use of Visual Mode has been confirmed.
    */
   @DefaultStringValue("")
   String visualModeConfirmedTitle();
   @DefaultStringValue("Whether or not the use of Visual Mode has been confirmed.")
   String visualModeConfirmedDescription();

   /**
    * The default type for new bibliographies.
    */
   @DefaultStringValue("")
   String bibliographyDefaultTypeTitle();
   @DefaultStringValue("The default type for new bibliographies.")
   String bibliographyDefaultTypeDescription();

   /**
    * The default style for inserting citations.
    */
   @DefaultStringValue("")
   String citationDefaultInTextTitle();
   @DefaultStringValue("The default style for inserting citations.")
   String citationDefaultInTextDescription();

   /**
    * Zotero connection type (local or web)
    */
   @DefaultStringValue("Zotero connection type")
   String zoteroConnectionTypeTitle();
   @DefaultStringValue("Zotero connection type (local or web)")
   String zoteroConnectionTypeDescription();

   /**
    * Whether to use Better BibTeX when suggesting citation keys and writing citations to BibTeX bibliographies
    */
   @DefaultStringValue("Use Better BibTeX for citation keys and BibTeX export")
   String zoteroUseBetterBibtexTitle();
   @DefaultStringValue("Whether to use Better BibTeX when suggesting citation keys and writing citations to BibTeX bibliographies")
   String zoteroUseBetterBibtexDescription();

   /**
    * Key for making Zotero API calls
    */
   @DefaultStringValue("Zotero API Key")
   String zoteroApiKeyTitle();
   @DefaultStringValue("Key for making Zotero API calls")
   String zoteroApiKeyDescription();

   /**
    * Directory containing Zotero data files
    */
   @DefaultStringValue("Zotero Data Directory")
   String zoteroDataDirTitle();
   @DefaultStringValue("Directory containing Zotero data files")
   String zoteroDataDirDescription();

   /**
    * Sync source editor to Quarto website preview navigation.
    */
   @DefaultStringValue("Quarto Website Sync Editor")
   String quartoWebsiteSyncEditorTitle();
   @DefaultStringValue("Sync source editor to Quarto website preview navigation.")
   String quartoWebsiteSyncEditorDescription();

   /**
    * Build Quarto editor tools (yaml.js) on the fly when requested.
    */
   @DefaultStringValue("Build Quarto Editor Tools")
   String quartoBuildEditorToolsTitle();
   @DefaultStringValue("Build Quarto editor tools (yaml.js) on the fly when requested.")
   String quartoBuildEditorToolsDescription();



}
