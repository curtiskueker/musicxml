package org.curtis.ui.swing.component;

import java.awt.GridBagConstraints;

public class ConstraintsFactory {
    private ConstraintsFactory() {

    }

    public static GridBagConstraints getNewConstraints() {
        return getNewConstraints(0, 0, 1, 1, 1, 1);
    }

    public static GridBagConstraints getNewConstraints(int rowNumber, int columnNumber, int gridHeight, int gridWidth, double weightx, double weighty) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = columnNumber;
        constraints.gridy = rowNumber;
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        return constraints;
    }
}
