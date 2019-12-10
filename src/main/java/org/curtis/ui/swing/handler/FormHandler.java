package org.curtis.ui.swing.handler;

import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.swing.StatusOutput;
import org.curtis.ui.swing.component.ComponentFactory;
import org.curtis.ui.swing.component.ConstraintsFactory;
import org.curtis.ui.swing.input.DataInput;
import org.curtis.ui.swing.input.DatabaseInput;
import org.curtis.ui.swing.input.DatabaseOutput;
import org.curtis.ui.swing.input.FromDatabase;
import org.curtis.ui.swing.input.FromInput;
import org.curtis.ui.swing.input.FromLilypond;
import org.curtis.ui.swing.input.FromMusicXml;
import org.curtis.ui.swing.input.InputRow;
import org.curtis.ui.swing.input.InputRowFactory;
import org.curtis.ui.swing.input.PanelRow;
import org.curtis.ui.swing.input.PropertiesInput;
import org.curtis.ui.swing.input.PropertiesOutput;
import org.curtis.ui.swing.input.ToDatabase;
import org.curtis.ui.swing.input.ToInput;
import org.curtis.ui.swing.input.ToLilypond;
import org.curtis.ui.swing.input.ToMusicXml;
import org.curtis.ui.swing.input.ToPdf;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.StringUtil;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormHandler {
    private ComponentHandler componentHandler = new ComponentHandler();
    private JPanel taskForm;
    private JLabel headerLabel;
    private JLabel convertLabel;
    private JPanel convertFromPanel;
    private JLabel convertArrowLabel;
    private JPanel convertToPanel;
    private JComboBox<String> fromFormat;
    private JComboBox<String> toFormat;
    private JTextArea statusTextArea;

    private List<PanelRow> inputPanels = new ArrayList<>();

    public static final String MENU_SET_PROPERTIES = "Set Properties";
    public static final String MENU_DATABASE_TASKS = "Database Tasks";
    public static final String MENU_CONVERSION_TASKS = "Conversion Tasks";
    public static final String MENU_SEPARATOR = "Menu Separator";
    public static final String MENU_EXIT_APPLICATION = "Exit";

    public static final String SUBMIT_BUTTON = "Submit";

    private static final double HORIZONTAL_SMALL_WEIGHT = .40;
    private static final double HORIZONTAL_LARGE_WEIGHT = .60;
    public static final double VERTICAL_CELL_WEIGHT = .08;
    private static final double VERTICAL_STATUS_WEIGHT = .20;

    private static final int NUMBER_OF_ROWS = 8;
    private int rowIndex = 0;

    private String menuSelection;
    private String convertFromSelection = "";
    private String convertToSelection = "";

    public FormHandler() {

    }

    public String getMenuSelection() {
        return menuSelection;
    }

    public void setMenuSelection(String menuSelection) {
        this.menuSelection = menuSelection;
    }

    public String getConvertFromSelection() {
        return convertFromSelection;
    }

    public void setConvertFromSelection(String convertFromSelection) {
        this.convertFromSelection = convertFromSelection;
    }

    public String getConvertToSelection() {
        return convertToSelection;
    }

    public void setConvertToSelection(String convertToSelection) {
        this.convertToSelection = convertToSelection;
    }

    public JPanel initializeForm() {
        taskForm = componentHandler.addNewPanel(null, 0, 0, 11, 2, 1, 1);
        headerLabel = componentHandler.addNewBoldLabelPanel(taskForm, 0, 0, 1, 2, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT, TaskConstants.TASKS_TITLE, 20);

        convertLabel = componentHandler.addNewLabelPanel(taskForm, 1, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        JPanel convertPanel = componentHandler.addNewPanel(taskForm, 1, 1, 1, 3, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        convertFromPanel = componentHandler.addNewPanel(convertPanel, 0, 0, .40, VERTICAL_CELL_WEIGHT);
        convertArrowLabel = componentHandler.addNewLabelPanel(convertPanel, 0, 1, .20, VERTICAL_CELL_WEIGHT);
        convertToPanel = componentHandler.addNewPanel(convertPanel, 0, 2, .40, VERTICAL_CELL_WEIGHT);

        for (int index = 0; index < NUMBER_OF_ROWS; index++) {
            int rowNumber = index + 2;
            PanelRow panelRow = componentHandler.addNewPanelRow(taskForm, rowNumber, HORIZONTAL_SMALL_WEIGHT, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
            inputPanels.add(panelRow);
        }

        JPanel statusPanel = ComponentFactory.newPanel();
        GridBagConstraints statusPanelConstraints = ConstraintsFactory.getNewConstraints(10, 0, 1, 2, 1, VERTICAL_STATUS_WEIGHT, GridBagConstraints.BOTH, GridBagConstraints.PAGE_END);
        componentHandler.addComponent(taskForm, statusPanel, statusPanelConstraints);
        JScrollPane statusScrollPane = componentHandler.addNewScrollPane(statusPanel);
        statusTextArea = new JTextArea();
        statusTextArea.setBackground(ComponentFactory.getBackgroundColor());
        statusTextArea.setEditable(false);
        statusTextArea.setRows(8);
        statusTextArea.setText("");
        statusScrollPane.setViewportView(statusTextArea);

        PrintStream statusPrintStream = new PrintStream(new StatusOutput(statusTextArea));
        System.setErr(statusPrintStream);
        System.setOut(statusPrintStream);

        return taskForm;
    }

    private void resetForm() {
        rowIndex = 0;

        for (int index = 0; index < NUMBER_OF_ROWS; index++) {
            PanelRow inputPanel = inputPanels.get(index);
            inputPanel.getLabel().setText("");
            JPanel panel = inputPanel.getInputPanel();
            panel.removeAll();
            GridBagLayout layout = (GridBagLayout)panel.getLayout();
            layout.setConstraints(panel, ConstraintsFactory.getNewConstraints());
        }

        componentHandler = new ComponentHandler();
        convertFromPanel.removeAll();
        convertToPanel.removeAll();
        headerLabel.setText(menuSelection);
    }


    public JMenuBar setupMenu() {
        return ComponentFactory.newMenu(
                "Tasks",
                Arrays.asList(
                        MENU_CONVERSION_TASKS, MENU_SET_PROPERTIES, MENU_DATABASE_TASKS,
                        MENU_SEPARATOR,
                        MENU_EXIT_APPLICATION
                )
        );
    }

    public JComboBox<String> setupFromFormatSelection() {
        fromFormat = ComponentFactory.newComboBox(Arrays.asList(TaskConstants.CONVERSION_TYPE_MUSICXML, TaskConstants.CONVERSION_TYPE_DATABASE, TaskConstants.CONVERSION_TYPE_LILYPOND));

        return fromFormat;
    }

    public JComboBox<String> setupToFormatSelection() {
        toFormat = ComponentFactory.newComboBox();

        return toFormat;
    }

    public void resetToFormatSelections(List<String> selections) {
        DefaultComboBoxModel<String> toModel = (DefaultComboBoxModel<String>)toFormat.getModel();
        toModel.removeAllElements();
        toModel.addElement("");
        toModel.addAll(selections);
    }

    public JButton handleSelection() {
        resetForm();

        if (menuSelection.equals(MENU_CONVERSION_TASKS)) {
            convertLabel.setText("Convert: ");
            convertArrowLabel.setText(" -> ");

            JComboBox fromSelection = fromFormat;
            fromSelection.setSelectedItem(convertFromSelection);
            componentHandler.addComponent(convertFromPanel, fromSelection);

            JComboBox toSelection = toFormat;
            toSelection.setSelectedItem(convertToSelection);
            componentHandler.addComponent(convertToPanel, toSelection);
        } else {
            convertLabel.setText("");
            convertArrowLabel.setText("");
        }

        FromInput fromInput = null;
        ToInput toInput = null;
        switch (menuSelection) {
            case MENU_SET_PROPERTIES:
                fromInput = new PropertiesInput();
                toInput = new PropertiesOutput();
                break;
            case MENU_DATABASE_TASKS:
                fromInput = new DatabaseInput();
                toInput = new DatabaseOutput();
                break;
            case MENU_CONVERSION_TASKS:
                switch (convertFromSelection) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        fromInput = new FromMusicXml();
                        break;
                    case TaskConstants.CONVERSION_TYPE_DATABASE:
                        fromInput = new FromDatabase();
                        break;
                    case TaskConstants.CONVERSION_TYPE_LILYPOND:
                        fromInput = new FromLilypond();
                        break;
                }
                switch (convertToSelection) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        toInput = new ToMusicXml();
                        break;
                    case TaskConstants.CONVERSION_TYPE_DATABASE:
                        toInput = new ToDatabase();
                        break;
                    case TaskConstants.CONVERSION_TYPE_LILYPOND:
                        toInput = new ToLilypond();
                        break;
                    case TaskConstants.CONVERSION_TYPE_PDF:
                        PropertiesHandler.addLocalPropertiesBundle();
                        if (StringUtil.isEmpty(PropertiesHandler.getOptionalProperty(PropertiesConstants.LILYPOND_LOCATION)))
                            addFormRow(InputRowFactory.newBoldLabel("Set Lilypond Location in Set Properties to create PDF file"));
                        else toInput = new ToPdf();
                        break;
                }
                break;
        }

        displayData(fromInput, toInput);
        clearStatusArea();
        taskForm.revalidate();

        return componentHandler.getSubmitButton();
    }

    public void handleTask() {
        clearStatusArea();
        TaskHandler.handleTask(componentHandler.getComponentMap(), menuSelection, convertFromSelection, convertToSelection);
    }

    private void displayData(DataInput fromInput, DataInput toInput) {
        if (fromInput == null || toInput == null) return;

        displayDataInput(fromInput);
        displayDataInput(toInput);
        addFormRow(InputRowFactory.newButton(SUBMIT_BUTTON));
    }

    private void displayDataInput(DataInput dataInput) {
        String title = dataInput.getTitle();
        if (StringUtil.isNotEmpty(title)) addFormRow(InputRowFactory.newBoldLabel(title));

        for (InputRow inputRow : dataInput.getInputRows()) {
            addFormRow(inputRow);
        }
    }

    private void addFormRow(InputRow inputRow) {
        componentHandler.addFormRow(inputPanels.get(rowIndex), inputRow);
        rowIndex++;
    }

    public void clearStatusArea() {
        try {
            statusTextArea.getDocument().remove(0, statusTextArea.getDocument().getLength());
            statusTextArea.update(statusTextArea.getGraphics());
        } catch (BadLocationException e) {
            //
        }
    }
}
