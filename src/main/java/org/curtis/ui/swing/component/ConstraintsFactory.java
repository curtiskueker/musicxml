package org.curtis.ui.swing.component;

import java.awt.GridBagConstraints;

public class ConstraintsFactory {
    private static final int DEFAULT = -1;

    private ConstraintsFactory() {

    }

    public static GridBagConstraints getNewConstraints() {
        return getNewConstraints(0, 0, 1, 1, 1, 1);
    }

    public static GridBagConstraints getNewConstraints(int fill) {
        return getNewConstraints(0, 0, 1, 1, 1, 1, fill);
    }

    public static GridBagConstraints getNewConstraints(int fill, int anchor) {
        return getNewConstraints(0, 0, 1, 1, 1, 1, fill, anchor);
    }

    public static GridBagConstraints getNewConstraints(int rowNumber, int columnNumber, int gridHeight, int gridWidth, double weightx, double weighty) {
        return getNewConstraints(rowNumber, columnNumber, gridHeight, gridWidth, weightx, weighty, DEFAULT, DEFAULT);
    }

    public static GridBagConstraints getNewConstraints(int rowNumber, int columnNumber, int gridHeight, int gridWidth, double weightx, double weighty, int fill) {
        return getNewConstraints(rowNumber, columnNumber, gridHeight, gridWidth, weightx, weighty, fill, DEFAULT);
    }

    public static GridBagConstraints getNewConstraints(int rowNumber, int columnNumber, int gridHeight, int gridWidth, double weightx, double weighty, int fill, int anchor) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = columnNumber;
        constraints.gridy = rowNumber;
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        if (fill != DEFAULT) constraints.fill = fill;
        else constraints.fill = GridBagConstraints.HORIZONTAL;
        if (anchor != DEFAULT) constraints.anchor = anchor;

        return constraints;
    }
}
