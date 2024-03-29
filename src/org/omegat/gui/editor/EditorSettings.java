/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool 
          with fuzzy matching, translation memory, keyword search, 
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2008 Alex Buloichik
               2012 Martin Fleurke, Hans-Peter Jacobs
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package org.omegat.gui.editor;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet;

import org.omegat.core.Core;
import org.omegat.core.data.SourceTextEntry.DUPLICATE;
import org.omegat.core.spellchecker.SpellCheckerMarker;
import org.omegat.util.Preferences;
import org.omegat.util.gui.Styles;
import org.omegat.util.gui.UIThreadsUtil;

/**
 * Editor behavior control settings.
 * 
 * @author Alex Buloichik (alex73mail@gmail.com)
 * @author Martin Fleurke
 * @author Hans-Peter Jacobs
 */
public class EditorSettings {
    private final EditorController parent;

    private boolean useTabForAdvance;
    private boolean markTranslated;
    private boolean markUntranslated;
    private boolean displaySegmentSources;
    private boolean markNonUniqueSegments;
    private boolean markNoted;
    private boolean markNBSP;
    private boolean markWhitespace;
    private boolean markBidi;
    private String displayModificationInfo;
    private boolean autoSpellChecking;
    private boolean viewSourceBold;
    private boolean markFirstNonUnique;

    public static String DISPLAY_MODIFICATION_INFO_NONE = "none";
    public static String DISPLAY_MODIFICATION_INFO_SELECTED = "selected";
    public static String DISPLAY_MODIFICATION_INFO_ALL = "all";

    protected EditorSettings(final EditorController parent) {
        this.parent = parent;

        //options from menu 'view'
        useTabForAdvance = Preferences.isPreference(Preferences.USE_TAB_TO_ADVANCE);
        markTranslated = Preferences.isPreference(Preferences.MARK_TRANSLATED_SEGMENTS);
        markUntranslated = Preferences.isPreference(Preferences.MARK_UNTRANSLATED_SEGMENTS);
        displaySegmentSources = Preferences.isPreference(Preferences.DISPLAY_SEGMENT_SOURCES);
        markNonUniqueSegments = Preferences.isPreference(Preferences.MARK_NON_UNIQUE_SEGMENTS);
        markNoted = Preferences.isPreference(Preferences.MARK_NOTED_SEGMENTS);
        markNBSP  = Preferences.isPreference(Preferences.MARK_NBSP);
        markWhitespace  = Preferences.isPreference(Preferences.MARK_WHITESPACE);
        markBidi  = Preferences.isPreference(Preferences.MARK_BIDI);
        displayModificationInfo = Preferences.getPreferenceDefault(Preferences.DISPLAY_MODIFICATION_INFO,
                DISPLAY_MODIFICATION_INFO_NONE);
        autoSpellChecking = Preferences.isPreference(Preferences.ALLOW_AUTO_SPELLCHECKING);

        //options from menu options->view
        viewSourceBold = Preferences.isPreference(Preferences.VIEW_OPTION_SOURCE_ALL_BOLD);
        markFirstNonUnique = Preferences.isPreference(Preferences.VIEW_OPTION_UNIQUE_FIRST);
    }

    public char getAdvancerChar() {
        if (useTabForAdvance) {
            return KeyEvent.VK_TAB;
        } else {
            return KeyEvent.VK_ENTER;
        }
    }

    public boolean isUseTabForAdvance() {
        return useTabForAdvance;
    }

    public void setUseTabForAdvance(boolean useTabForAdvance) {
        this.useTabForAdvance = useTabForAdvance;
        Preferences.setPreference(Preferences.USE_TAB_TO_ADVANCE, useTabForAdvance);
    }

    public boolean isMarkTranslated() {
        return markTranslated;
    }

    public void setMarkTranslated(boolean markTranslated) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.markTranslated = markTranslated;
        Preferences.setPreference(Preferences.MARK_TRANSLATED_SEGMENTS, markTranslated);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }

    public boolean isMarkUntranslated() {
        return markUntranslated;
    }

    public void setMarkUntranslated(boolean markUntranslated) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.markUntranslated = markUntranslated;
        Preferences.setPreference(Preferences.MARK_UNTRANSLATED_SEGMENTS, markUntranslated);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }

    /** display the segment sources or not */
    public boolean isDisplaySegmentSources() {
        return displaySegmentSources;
    }

    public boolean isMarkNonUniqueSegments() {
        return markNonUniqueSegments;
    }

    public boolean isMarkNotedSegments() {
        return markNoted;
    }

    /**
     * mark non-breakable spaces?
     * @return true when set, false otherwise
     */
    public boolean isMarkNBSP() {
        return markNBSP;
    }
    /**
     * mark whitespace?
     * @return true when set, false otherwise
     */
    public boolean isMarkWhitespace() {
        return markWhitespace;
    }
    /**
     * mark Bidirectional control characters
     * @return true when set, false otherwise
     */
    public boolean isMarkBidi() {
        return markBidi;
    }

    public void setDisplaySegmentSources(boolean displaySegmentSources) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.displaySegmentSources = displaySegmentSources;
        Preferences.setPreference(Preferences.DISPLAY_SEGMENT_SOURCES, displaySegmentSources);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }

    public void setMarkNonUniqueSegments(boolean markNonUniqueSegments) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.markNonUniqueSegments = markNonUniqueSegments;
        Preferences.setPreference(Preferences.MARK_NON_UNIQUE_SEGMENTS, markNonUniqueSegments);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }

    public void setMarkNotedSegments(boolean markNotedSegments) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.markNoted = markNotedSegments;
        Preferences.setPreference(Preferences.MARK_NOTED_SEGMENTS, markNoted);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }
    
    public void setMarkNBSP(boolean markNBSP) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.markNBSP = markNBSP;
        Preferences.setPreference(Preferences.MARK_NBSP, markNBSP);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }
    public void setMarkWhitespace(boolean markWhitespace) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.markWhitespace = markWhitespace;
        Preferences.setPreference(Preferences.MARK_WHITESPACE, markWhitespace);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }
    public void setMarkBidi(boolean markBidi) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.markBidi = markBidi;
        Preferences.setPreference(Preferences.MARK_BIDI, markBidi);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }

    /**
     * returns the setting for display the modification information or not
     * Either DISPLAY_MODIFICATION_INFO_NONE,
     * DISPLAY_MODIFICATION_INFO_SELECTED, DISPLAY_MODIFICATION_INFO_ALL
     */
    public String getDisplayModificationInfo() {
        return displayModificationInfo;
    }

    /**
     * Sets the setting for display the modification information or not
     * 
     * @param displayModificationInfo
     *            Either DISPLAY_MODIFICATION_INFO_NONE ,
     *            DISPLAY_MODIFICATION_INFO_SELECTED ,
     *            DISPLAY_MODIFICATION_INFO_ALL
     */
    public void setDisplayModificationInfo(String displayModificationInfo) {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        this.displayModificationInfo = displayModificationInfo;
        Preferences.setPreference(Preferences.DISPLAY_MODIFICATION_INFO, displayModificationInfo);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }

    /** need to check spell or not */
    public boolean isAutoSpellChecking() {
        return autoSpellChecking;
    }

    public void setAutoSpellChecking(boolean autoSpellChecking) {
        UIThreadsUtil.mustBeSwingThread();
        if (Core.getProject().isProjectLoaded()) {
            parent.commitAndDeactivate();
        }

        this.autoSpellChecking = autoSpellChecking;

        if (Core.getProject().isProjectLoaded()) {
            // parent.loadDocument();
            parent.activateEntry();
            parent.remarkOneMarker(SpellCheckerMarker.class.getName());
        }
    }
    
    /**
     * repaint segments in editor according to new view options. Use when options change to make them effective immediately.
     */
    public void updateViewPreferences() {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        //update variables
        viewSourceBold = Preferences.isPreference(Preferences.VIEW_OPTION_SOURCE_ALL_BOLD);
        markFirstNonUnique = Preferences.isPreference(Preferences.VIEW_OPTION_UNIQUE_FIRST);

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }
    /**
     * repaint segments in editor according to new view tag validation options. Use when options change to make them effective immediately.
     */
    public void updateTagValidationPreferences() {
        UIThreadsUtil.mustBeSwingThread();

        parent.commitAndDeactivate();

        //nothing special to do: tags/placeholders are determined by segment builder and info is passed as argument to getattributeSet.

        if (Core.getProject().isProjectLoaded()) {
            parent.loadDocument();
            parent.activateEntry();
        }
    }
    
    /**
     * Choose segment's attributes based on rules.
     * @param isSource is it a source segment or a target segment
     * @param isPlaceholder is it for a placeholder (OmegaT tag or sprintf-variable etc.) or regular text inside the segment?
     * @param isremovetext is it text that should be removed from translation?
     * @param duplicate is the sourceTextEntry a duplicate or not? values: DUPLICATE.NONE, DUPLICATE.FIRST or DUPLICATE.NEXT. See sourceTextEntryste.getDuplicate()
     * @param active is it an active segment?
     * @param translationExists does a translation already exist
     * @param isNBSP is the text a non-breakable space
     * @return proper AttributeSet to use on displaying the segment.
     */
    public AttributeSet getAttributeSet(boolean isSource, boolean isPlaceholder, boolean isRemoveText, DUPLICATE duplicate, boolean active, boolean translationExists, boolean hasNote, boolean isNBSP) {
        //determine foreground color
        Color fg = null;
        if (markNonUniqueSegments) {
            switch (duplicate) {
            case NONE:
                break;
            case FIRST:
                if (markFirstNonUnique) {
                    fg = Styles.COLOR_NON_UNIQUE;
                }
                break;
            case NEXT:
                fg = Styles.COLOR_NON_UNIQUE;
                break;
            }
        }
        if (isPlaceholder) fg = Styles.COLOR_PLACEHOLDER;
        if (isRemoveText && !isSource) {
            fg = Styles.COLOR_REMOVETEXT_TARGET;
        }
        
        //determine background color
        Color bg = null;
        if (active) {
            if (isSource) {
                bg = Styles.COLOR_SOURCE;
            }
        } else {
            if (isSource) {
                if (isMarkNotedSegments() && hasNote && !translationExists) {
                    bg = Styles.COLOR_NOTED;
                } else if (markUntranslated && !translationExists) {
                    bg = Styles.COLOR_UNTRANSLATED;
                } else if (isDisplaySegmentSources()) {
                    bg = Styles.COLOR_SOURCE;
                }
            } else {
                if (isMarkNotedSegments() && hasNote) {
                    bg = Styles.COLOR_NOTED;
                } else if (markTranslated) {
                    bg = Styles.COLOR_TRANSLATED;
                }
            }
        }
        if (isNBSP && isMarkNBSP()) { //overwrite others, because space is smallest.
            bg = Styles.COLOR_NBSP;
        }

        //determine bold
        Boolean bold = false;
        if (isSource) {
            if (active || viewSourceBold && isDisplaySegmentSources()) {
                bold = true;
            }
        }

        //determine italic
        Boolean italic = false;
        if (isRemoveText && isSource) {
            italic = true;
        }

        return Styles.createAttributeSet(fg, bg, bold, italic);
    }
    /**
     * Returns font attributes for the modification info line.
     * @return
     */
    public AttributeSet getModificationInfoAttributeSet() {
        return Styles.createAttributeSet(null, null, null, true);
    }
    /**
     * Returns font attributes for the segment marker.
     * @return
     */
    public AttributeSet getSegmentMarkerAttributeSet() {
        return Styles.createAttributeSet(null, null, true, false);
    }
    /**
     * Returns font attributes for other laguages translation.
     * @return
     */
    public AttributeSet getOtherLanguageTranslationAttributeSet() {
        return Styles.createAttributeSet(null, Styles.COLOR_SOURCE, false, true);
    }
}
