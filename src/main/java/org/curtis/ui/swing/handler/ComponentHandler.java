package org.curtis.ui.swing.handler;

import org.curtis.ui.swing.component.ComponentFactory;
import org.curtis.ui.swing.component.ConstraintsFactory;
import org.curtis.ui.swing.input.PanelRow;
import org.curtis.ui.swing.input.InputRow;
import org.curtis.util.StringUtil;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

public class ComponentHandler {
    private Map<String, Component> componentMap = new HashMap<>();

    private static final String SHOW_PASSWORD = "Show password";

    public ComponentHandler() {

    }

    public Map<String, Component> getComponentMap() {
        return componentMap;
    }

    public void addFormRow(PanelRow panelRow, InputRow inputRow) {
        panelRow.getLabel().setText(inputRow.getText());
        addFormElement(panelRow.getInputPanel(), inputRow);
    }

    private void addFormElement(JPanel panel, InputRow inputRow) {
        Component component = null;

        switch (inputRow.getInputType()) {
            case LABEL:
                JLabel label;
                String labelText = inputRow.getValue();
                if (inputRow.isBoldText()) label = ComponentFactory.newBoldLabel(labelText, 16);
                else label = ComponentFactory.newLabel(labelText);
                panel.add(label);
                break;
            case INPUT:
                JTextField textField = ComponentFactory.newTextField(inputRow.getValue(), inputRow.getInputSize());
                GridBagConstraints inputConstraints = ConstraintsFactory.getNewConstraints(GridBagConstraints.NONE, GridBagConstraints.LINE_START);
                addComponent(panel, textField, inputConstraints);
                component = textField;
                break;
            case PASSWORD:
                JPanel leftPanel = addNewPanel(panel, 0, 0, .60, FormHandler.VERTICAL_CELL_WEIGHT);
                JPanel rightPanel = addNewPanel(panel, 0, 1, .40, FormHandler.VERTICAL_CELL_WEIGHT);
                JPasswordField passwordField = ComponentFactory.newPasswordField(inputRow.getValue());
                addComponent(leftPanel, passwordField);

                JCheckBox showPassword = ComponentFactory.newCheckBox(SHOW_PASSWORD, SwingConstants.RIGHT);
                showPassword.addItemListener(e -> {
                    if (showPassword.isSelected()) passwordField.setEchoChar((char) 0);
                    else passwordField.setEchoChar('*');
                });
                addComponent(rightPanel, showPassword);

                component = passwordField;
                break;
            case INPUT_FILE:
                JFileChooser inputFileChooser = ComponentFactory.newFileChooser(inputRow.getValue(), inputRow.getSelectedFilename());
                addComponent(panel, inputFileChooser);
                component = inputFileChooser;
                break;
            case DIRECTORY:
                JFileChooser outputFileChooser = ComponentFactory.newDirectoryChooser();
                addComponent(panel, outputFileChooser);
                component = outputFileChooser;
                break;
            case SELECTION:
                JComboBox<String> selection = ComponentFactory.newComboBox(inputRow.getSelectionList(), inputRow.getSelectedItem());
                addComponent(panel, selection);
                component = selection;
                break;
            case CHECKBOX:
                JCheckBox checkBox = ComponentFactory.newCheckBox();
                addComponent(panel, checkBox);
                component = checkBox;
                break;
            case BUTTON:
                JButton button = ComponentFactory.newButton(inputRow.getName());
                GridBagConstraints buttonConstraints = ConstraintsFactory.getNewConstraints(GridBagConstraints.NONE, GridBagConstraints.LINE_START);
                addComponent(panel, button, buttonConstraints);
                component = button;
                break;
        }

        if (component != null && StringUtil.isNotEmpty(inputRow.getName())) componentMap.put(inputRow.getName(), component);
    }

    public void addComponent(JComponent parentComponent, JComponent childComponent) {
        addComponent(parentComponent, childComponent, ConstraintsFactory.getNewConstraints());
    }

    public void addComponent(JComponent parentComponent, JComponent childComponent, GridBagConstraints constraints) {
        if (parentComponent != null) parentComponent.add(childComponent, constraints);
    }

    public JPanel addNewPanel(JComponent parentComponent, int rowNumber, int columnNumber, double weightx, double weighty) {
        return addNewPanel(parentComponent, rowNumber, columnNumber, 1, 1, weightx, weighty);
    }

    public JPanel addNewPanel(JComponent parentComponent, int rowNumber, int columnNumber, int gridHeight, int gridWidth, double weightx, double weighty) {
        JPanel panel = ComponentFactory.newPanel();
        addComponent(parentComponent, panel, ConstraintsFactory.getNewConstraints(rowNumber, columnNumber, gridHeight, gridWidth, weightx, weighty));

        return panel;
    }

    public JLabel addNewLabel(JComponent parentComponent) {
        JLabel label = ComponentFactory.newLabel("");
        parentComponent.add(label);

        return label;
    }

    public JLabel addNewBoldLabel(JComponent parentComponent, String text, int size) {
        JLabel label = ComponentFactory.newBoldLabel(text, size);
        parentComponent.add(label);

        return label;
    }

    public JLabel addNewLabelPanel(JComponent parentComponent, int rowNumber, int columnNumber, double weightx, double weighty) {
        JPanel panel = addNewPanel(parentComponent, rowNumber, columnNumber, 1, 1, weightx, weighty);

        return addNewLabel(panel);
    }

    public JLabel addNewBoldLabelPanel(JComponent parentComponent, int rowNumber, int columnNumber, int gridHeight, int gridWidth, double weightx, double weighty, String text, int size) {
        JPanel panel = addNewPanel(parentComponent, rowNumber, columnNumber, gridHeight, gridWidth, weightx, weighty);

        return addNewBoldLabel(panel, text, size);
    }

    public JScrollPane addNewScrollPane(JComponent parentComponent) {
        JScrollPane scrollPane = ComponentFactory.newScrollPane();
        GridBagConstraints constraints = ConstraintsFactory.getNewConstraints(GridBagConstraints.BOTH);
        addComponent(parentComponent, scrollPane, constraints);

        return scrollPane;
    }

    public PanelRow addNewPanelRow(JComponent parentComponent, int rowNumber, double leftWeightx, double rightWeightx, double weighty) {
        JLabel label = addNewLabelPanel(parentComponent, rowNumber, 0, leftWeightx, weighty);
        JPanel inputPanel = addNewPanel(parentComponent, rowNumber, 1, rightWeightx, weighty);

        return new PanelRow(label, inputPanel);
    }

    public JButton getSubmitButton() {
        return (JButton)componentMap.get(FormHandler.SUBMIT_BUTTON);
    }
}
