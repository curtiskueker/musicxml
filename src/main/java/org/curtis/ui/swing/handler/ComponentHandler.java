package org.curtis.ui.swing.handler;

import org.curtis.ui.swing.MusicXmlTasks;
import org.curtis.ui.swing.component.ComponentFactory;
import org.curtis.ui.swing.component.ConstraintsFactory;
import org.curtis.ui.swing.input.DataInput;
import org.curtis.ui.swing.input.InputPanel;
import org.curtis.ui.swing.input.InputRow;
import org.curtis.ui.swing.input.InputRowFactory;
import org.curtis.ui.task.TaskConstants;
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
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentHandler {
    private List<InputPanel> inputPanels;
    private Map<String, Component> componentMap = new HashMap<>();
    private static final int NUMBER_OF_ROWS = 8;
    private int rowIndex = 0;

    public ComponentHandler(List<InputPanel> inputPanels) {
        this.inputPanels = inputPanels;
    }

    public Map<String, Component> getComponentMap() {
        return componentMap;
    }

    public void displayData(DataInput fromInput, DataInput toInput) {
        if (fromInput == null || toInput == null) return;

        displayDataInput(fromInput);
        displayDataInput(toInput);
        addFormRow(InputRowFactory.newButton(TaskConstants.SUBMIT_BUTTON));
    }

    private void displayDataInput(DataInput dataInput) {
        String title = dataInput.getTitle();
        if (StringUtil.isNotEmpty(title)) addFormRow(InputRowFactory.newBoldLabel(title));

        for (InputRow inputRow : dataInput.getInputRows()) {
            inputPanels.get(rowIndex).getLabel().setText(inputRow.getText());
            addFormRow(inputRow);
        }
    }

    public void addFormRow(InputRow inputRow) {
        addFormElement(inputPanels.get(rowIndex).getPanel(), inputRow);
        rowIndex++;
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
                JPanel leftPanel = addNewPanel(panel, 0, 0, .60, MusicXmlTasks.VERTICAL_CELL_WEIGHT);
                JPanel rightPanel = addNewPanel(panel, 0, 1, .40, MusicXmlTasks.VERTICAL_CELL_WEIGHT);
                JPasswordField passwordField = ComponentFactory.newPasswordField(inputRow.getValue());
                addComponent(leftPanel, passwordField);

                JCheckBox showPassword = ComponentFactory.newCheckBox(TaskConstants.SHOW_PASSWORD, SwingConstants.RIGHT);
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

    public void resetFormElements() {
        for (int index = 0; index < NUMBER_OF_ROWS; index++) {
            InputPanel inputPanel = inputPanels.get(index);
            inputPanel.getLabel().setText("");
            JPanel panel = inputPanel.getPanel();
            panel.removeAll();
            GridBagLayout layout = (GridBagLayout)panel.getLayout();
            layout.setConstraints(panel, ConstraintsFactory.getNewConstraints());
        }
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

    public JPanel addNewPanel(JComponent parentComponent, int rowNumber, int columnNumber,int gridHeight, int gridWidth, double weightx, double weighty) {
        JPanel panel = ComponentFactory.newPanel();
        addComponent(parentComponent, panel, ConstraintsFactory.getNewConstraints(rowNumber, columnNumber, gridHeight, gridWidth, weightx, weighty));

        return panel;
    }

    public JLabel addNewLabel(JComponent parentComponent) {
        JLabel label = ComponentFactory.newLabel("");
        parentComponent.add(label);

        return label;
    }

    public JScrollPane addNewScrollPane(JComponent parentComponent) {
        JScrollPane scrollPane = ComponentFactory.newScrollPane();
        GridBagConstraints constraints = ConstraintsFactory.getNewConstraints(GridBagConstraints.BOTH);
        addComponent(parentComponent, scrollPane, constraints);

        return scrollPane;
    }
}
