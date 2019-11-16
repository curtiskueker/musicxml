package org.curtis.ui.swing.input;

import javax.swing.JPanel;

public class PanelRow {
    private LabelPanel labelPanel;
    private JPanel inputPanel;

    public PanelRow(LabelPanel labelPanel, JPanel inputPanel) {
        this.labelPanel = labelPanel;
        this.inputPanel = inputPanel;
    }

    public LabelPanel getLabelPanel() {
        return labelPanel;
    }

    public void setLabelPanel(LabelPanel labelPanel) {
        this.labelPanel = labelPanel;
    }

    public JPanel getInputPanel() {
        return inputPanel;
    }

    public void setInputPanel(JPanel inputPanel) {
        this.inputPanel = inputPanel;
    }
}
