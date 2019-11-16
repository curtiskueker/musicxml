package org.curtis.ui.swing.input;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelRow {
    private JLabel label;
    private JPanel inputPanel;

    public PanelRow(JLabel label, JPanel inputPanel) {
        this.label = label;
        this.inputPanel = inputPanel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JPanel getInputPanel() {
        return inputPanel;
    }

    public void setInputPanel(JPanel inputPanel) {
        this.inputPanel = inputPanel;
    }
}
