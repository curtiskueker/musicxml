package org.curtis.ui.swing.input;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InputPanel {
    private JLabel label;
    private JPanel panel;

    public InputPanel(JLabel label, JPanel panel) {
        this.label = label;
        this.panel = panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JPanel getPanel() {
        return panel;
    }
}
