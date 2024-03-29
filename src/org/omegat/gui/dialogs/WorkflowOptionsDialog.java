/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool 
          with fuzzy matching, translation memory, keyword search, 
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2000-2006 Keith Godfrey and Maxym Mykhalchuk
               2009 Didier Briel
               2011 John Moran, Didier Briel
               2012 Didier Briel
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

package org.omegat.gui.dialogs;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import org.omegat.util.OStrings;
import org.omegat.util.Preferences;

/**
 * 
 * @author Maxym Mykhalchuk
 * @author Didier Briel
 * @author John Moran
 */
@SuppressWarnings("serial")
public class WorkflowOptionsDialog extends JDialog {
    /** A return status code - returned if Cancel button has been pressed */
    public static final int RET_CANCEL = 0;
    /** A return status code - returned if OK button has been pressed */
    public static final int RET_OK = 1;

    /** Creates new form WorkflowOptionsDialog */
    public WorkflowOptionsDialog(Frame parent) {
        super(parent, true);

        // HP
        // Handle escape key to close the window
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
        // END HP

        initComponents();

        getRootPane().setDefaultButton(okButton);

        // initializing options
        leaveEmptyRadio.setSelected(Preferences.isPreference(Preferences.DONT_INSERT_SOURCE_TEXT));

        insertFuzzyCheckBox.setSelected(Preferences.isPreference(Preferences.BEST_MATCH_INSERT));
        similarityLabel.setEnabled(insertFuzzyCheckBox.isSelected());
        similaritySpinner
                .setValue(new Integer(Preferences.getPreferenceDefault(
                        Preferences.BEST_MATCH_MINIMAL_SIMILARITY,
                        Preferences.BEST_MATCH_MINIMAL_SIMILARITY_DEFAULT)));
        similaritySpinner.setEnabled(insertFuzzyCheckBox.isSelected());
        prefixLabel.setEnabled(insertFuzzyCheckBox.isSelected());
        if (!Preferences.existsPreference(Preferences.BEST_MATCH_EXPLANATORY_TEXT)) {
            prefixText.setText(OStrings.getString("WF_DEFAULT_PREFIX"));
        } else {
            prefixText.setText(Preferences.getPreferenceDefaultAllowEmptyString(
                                         Preferences.BEST_MATCH_EXPLANATORY_TEXT));
        }
        prefixText.setEnabled(insertFuzzyCheckBox.isSelected());

        allowTranslationEqualToSource.setSelected(Preferences
                .isPreference(Preferences.ALLOW_TRANS_EQUAL_TO_SRC));
        exportCurrentSegment.setSelected(Preferences.isPreference(Preferences.EXPORT_CURRENT_SEGMENT));
        stopOnAlternativeTranslation.setSelected(Preferences.
                isPreference(Preferences.STOP_ON_ALTERNATIVE_TRANSLATION));
        convertNumbers.setSelected(Preferences.isPreference(Preferences.CONVERT_NUMBERS));
        allowTagEditing.setSelected(Preferences.isPreference(Preferences.ALLOW_TAG_EDITING));
        tagValidateOnLeave.setSelected(Preferences.isPreference(Preferences.TAG_VALIDATE_ON_LEAVE));
        invalidate();
        pack();
    }

    /** @return the return status of this dialog - one of RET_OK or RET_CANCEL */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        ourButtonGroup = new javax.swing.ButtonGroup();
        similarityLabel = new javax.swing.JLabel();
        similaritySpinner = new javax.swing.JSpinner();
        descriptionTextArea = new javax.swing.JTextArea();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        convertNumbers = new javax.swing.JCheckBox();
        allowTranslationEqualToSource = new javax.swing.JCheckBox();
        defaultRadio = new javax.swing.JRadioButton();
        leaveEmptyRadio = new javax.swing.JRadioButton();
        prefixLabel = new javax.swing.JLabel();
        prefixText = new javax.swing.JTextField();
        insertFuzzyCheckBox = new javax.swing.JCheckBox();
        exportCurrentSegment = new javax.swing.JCheckBox();
        stopOnAlternativeTranslation = new javax.swing.JCheckBox();
        allowTagEditing = new javax.swing.JCheckBox();
        tagValidateOnLeave = new javax.swing.JCheckBox();

        setTitle(OStrings.getString("GUI_TITLE_Workflow_Options")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        similarityLabel.setLabelFor(similaritySpinner);
        org.openide.awt.Mnemonics.setLocalizedText(similarityLabel, OStrings.getString("GUI_WORKFLOW_OPTION_Minimal_Similarity")); // NOI18N
        similarityLabel.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 16, 4, 4);
        getContentPane().add(similarityLabel, gridBagConstraints);

        similaritySpinner.setEnabled(false);
        similaritySpinner.setValue(new Integer(90));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(similaritySpinner, gridBagConstraints);

        descriptionTextArea.setBackground(javax.swing.UIManager.getDefaults().getColor("Label.background"));
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setFont(new JLabel().getFont());
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setText(OStrings.getString("GUI_WORKFLOW_DESCRIPTION")); // NOI18N
        descriptionTextArea.setWrapStyleWord(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(descriptionTextArea, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(okButton, OStrings.getString("BUTTON_OK")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 4, 4, 4);
        getContentPane().add(okButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, OStrings.getString("BUTTON_CANCEL")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 4, 4, 4);
        getContentPane().add(cancelButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(convertNumbers, OStrings.getString("WF_OPTION_CONVERT_NUMBERS")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(convertNumbers, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(allowTranslationEqualToSource, OStrings.getString("WF_OPTION_ALLOW_TRANS_EQ_TO_SRC")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(allowTranslationEqualToSource, gridBagConstraints);

        ourButtonGroup.add(defaultRadio);
        defaultRadio.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(defaultRadio, OStrings.getString("WF_OPTION_INSERT_SOURCE")); // NOI18N
        defaultRadio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        defaultRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 4);
        getContentPane().add(defaultRadio, gridBagConstraints);

        ourButtonGroup.add(leaveEmptyRadio);
        org.openide.awt.Mnemonics.setLocalizedText(leaveEmptyRadio, OStrings.getString("WF_OPTION_INSERT_NOTHTHING")); // NOI18N
        leaveEmptyRadio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leaveEmptyRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 4);
        getContentPane().add(leaveEmptyRadio, gridBagConstraints);

        prefixLabel.setLabelFor(prefixText);
        org.openide.awt.Mnemonics.setLocalizedText(prefixLabel, OStrings.getString("WF_OPTION_INSERT_FUZZY_PREFIX")); // NOI18N
        prefixLabel.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 16, 4, 4);
        getContentPane().add(prefixLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(prefixText, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(insertFuzzyCheckBox, OStrings.getString("WF_OPTION_INSERT_FUZZY_MATCH")); // NOI18N
        insertFuzzyCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 4);
        getContentPane().add(insertFuzzyCheckBox, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(exportCurrentSegment, OStrings.getString("WF_OPTION_EXPORT__CURRENT_SEGMENT")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(exportCurrentSegment, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(stopOnAlternativeTranslation, OStrings.getString("WF_OPTION_GOTO_NEXT_UNTRANSLATED")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(stopOnAlternativeTranslation, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(allowTagEditing, OStrings.getString("WF_TAG_EDITING")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(allowTagEditing, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(tagValidateOnLeave, OStrings.getString("WG_TAG_VALIDATE_ON_LEAVE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(tagValidateOnLeave, gridBagConstraints);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void radiosActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_radiosActionPerformed
    {// GEN-HEADEREND:event_radiosActionPerformed
        similarityLabel.setEnabled(insertFuzzyCheckBox.isSelected());
        similaritySpinner.setEnabled(insertFuzzyCheckBox.isSelected());
        prefixLabel.setEnabled(insertFuzzyCheckBox.isSelected());
        prefixText.setEnabled(insertFuzzyCheckBox.isSelected());
    }// GEN-LAST:event_radiosActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_okButtonActionPerformed
    {
        Preferences.setPreference(Preferences.DONT_INSERT_SOURCE_TEXT, leaveEmptyRadio.isSelected());

        Preferences.setPreference(Preferences.BEST_MATCH_INSERT, insertFuzzyCheckBox.isSelected());
        if (insertFuzzyCheckBox.isSelected()) {
            Preferences.setPreference(Preferences.BEST_MATCH_MINIMAL_SIMILARITY, similaritySpinner.getValue()
                    .toString());
            Preferences.setPreference(Preferences.BEST_MATCH_EXPLANATORY_TEXT, prefixText.getText());
        }

        Preferences.setPreference(Preferences.ALLOW_TRANS_EQUAL_TO_SRC,
                allowTranslationEqualToSource.isSelected());
        Preferences.setPreference(Preferences.EXPORT_CURRENT_SEGMENT, exportCurrentSegment.isSelected());
        Preferences.setPreference(Preferences.STOP_ON_ALTERNATIVE_TRANSLATION,
                stopOnAlternativeTranslation.isSelected());
        Preferences.setPreference(Preferences.CONVERT_NUMBERS, convertNumbers.isSelected());
        Preferences.setPreference(Preferences.ALLOW_TAG_EDITING, allowTagEditing.isSelected());
        Preferences.setPreference(Preferences.TAG_VALIDATE_ON_LEAVE, tagValidateOnLeave.isSelected());

        doClose(RET_OK);
    }// GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_cancelButtonActionPerformed
    {
        doClose(RET_CANCEL);
    }// GEN-LAST:event_cancelButtonActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt)// GEN-FIRST:event_closeDialog
    {
        doClose(RET_CANCEL);
    }// GEN-LAST:event_closeDialog

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox allowTagEditing;
    private javax.swing.JCheckBox allowTranslationEqualToSource;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox convertNumbers;
    private javax.swing.JRadioButton defaultRadio;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JCheckBox exportCurrentSegment;
    private javax.swing.JCheckBox insertFuzzyCheckBox;
    private javax.swing.JRadioButton leaveEmptyRadio;
    private javax.swing.JButton okButton;
    private javax.swing.ButtonGroup ourButtonGroup;
    private javax.swing.JLabel prefixLabel;
    private javax.swing.JTextField prefixText;
    private javax.swing.JLabel similarityLabel;
    private javax.swing.JSpinner similaritySpinner;
    private javax.swing.JCheckBox stopOnAlternativeTranslation;
    private javax.swing.JCheckBox tagValidateOnLeave;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
