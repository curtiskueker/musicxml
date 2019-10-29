package org.curtis.ui.swing;

import org.curtis.properties.AppProperties;
import org.curtis.ui.swing.input.DataInput;
import org.curtis.ui.swing.input.DatabaseInput;
import org.curtis.ui.swing.input.DatabaseOutput;
import org.curtis.ui.swing.input.FromDatabase;
import org.curtis.ui.swing.input.FromInput;
import org.curtis.ui.swing.input.FromLilypond;
import org.curtis.ui.swing.input.FromMusicXml;
import org.curtis.ui.swing.input.InputRow;
import org.curtis.ui.swing.input.InputType;
import org.curtis.ui.swing.input.PropertiesInput;
import org.curtis.ui.swing.input.PropertiesOutput;
import org.curtis.ui.swing.input.ToDatabase;
import org.curtis.ui.swing.input.ToInput;
import org.curtis.ui.swing.input.ToLilypond;
import org.curtis.ui.swing.input.ToMusicXml;
import org.curtis.ui.swing.input.ToPdf;
import org.curtis.ui.task.DatabaseTask;
import org.curtis.ui.task.Db2PdfTask;
import org.curtis.ui.task.Ly2PdfTask;
import org.curtis.ui.task.MusicXml2PdfTask;
import org.curtis.ui.task.SetPropertiesTask;
import org.curtis.ui.task.Db2LyTask;
import org.curtis.ui.task.Db2MusicXmlTask;
import org.curtis.ui.task.MusicXml2DbTask;
import org.curtis.ui.task.MusicXml2LyTask;
import org.curtis.ui.task.MusicXmlTask;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.StringUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicXmlTasks {
    private JPanel taskForm;
    private JPanel header;
    private JPanel row3Left;
    private JPanel row3Right;
    private JPanel row4Left;
    private JPanel row4Right;
    private JPanel row5Left;
    private JPanel row5Right;
    private JPanel row6Left;
    private JPanel row6Right;
    private JPanel row7Left;
    private JPanel row7Right;
    private JPanel row8Left;
    private JPanel row8Right;
    private JPanel row9Left;
    private JPanel row9Right;
    private JPanel row10Left;
    private JPanel row10Right;
    private JLabel headerLabel;
    private JLabel formElement1Text;
    private JLabel formElement2Text;
    private JLabel formElement3Text;
    private JLabel formElement4Text;
    private JLabel formElement5Text;
    private JLabel formElement6Text;
    private JLabel formElement7Text;
    private JLabel formElement8Text;
    private JPanel statusPanel;
    private JScrollPane statusScrollPane;
    private JTextArea statusTextArea;
    private JPanel convertLabelPanel;
    private JLabel convertLabel;
    private JPanel convertPanel;
    private JPanel convertFromPanel;
    private JPanel convertArrowPanel;
    private JPanel convertToPanel;
    private JLabel convertArrowLabel;
    private JCheckBox showPassword;
    private String fromSelectedValue = "";
    private String toSelectedValue = "";
    private JComboBox fromFormat;
    private JComboBox toFormat;

    private String selectedValue;
    private Map<String, Component> componentMap = new HashMap<>();

    private List<JLabel> formElementTextLabels = new ArrayList<>();
    private List<JPanel> rightPanels = new ArrayList<>();
    private static final int NUMBER_OF_ROWS = 8;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private int rowIndex = 0;

    private static final int CHOOSER_SIZE = 450;

    public MusicXmlTasks() {
        setupStatusArea();
        setupFormatSelections();
        setupFormElements();
    }

    private void handleSelection() {
        resetFormElements();

        convertFromPanel.removeAll();
        convertToPanel.removeAll();

        componentMap.clear();

        headerLabel.setText(selectedValue);

        if (selectedValue.equals("Conversion Tasks")) {
            convertLabel.setText("Convert: ");
            convertArrowLabel.setText(" -> ");

            JComboBox fromSelection = fromFormat;
            fromSelection.setSelectedItem(fromSelectedValue);
            convertFromPanel.add(fromSelection, getConstraints());

            JComboBox toSelection = toFormat;
            toSelection.setSelectedItem(toSelectedValue);
            convertToPanel.add(toSelection, getConstraints());
        } else {
            convertLabel.setText("");
            convertArrowLabel.setText("");
        }

        rowIndex = 0;
        FromInput fromInput = null;
        ToInput toInput = null;
        switch (selectedValue) {
            case "Set Properties":
                fromInput = new PropertiesInput();
                toInput = new PropertiesOutput();
                break;
            case "Database Tasks":
                fromInput = new DatabaseInput();
                toInput = new DatabaseOutput();
                break;
            case "Conversion Tasks":
                switch (fromSelectedValue) {
                    case "MusicXml File":
                        fromInput = new FromMusicXml();
                        break;
                    case "Database Record":
                        fromInput = new FromDatabase();
                        break;
                    case "Lilypond File":
                        fromInput = new FromLilypond();
                        break;
                }
                switch (toSelectedValue) {
                    case "MusicXml File":
                        toInput = new ToMusicXml();
                        break;
                    case "Database Record":
                        toInput = new ToDatabase();
                        break;
                    case "Lilypond File":
                        toInput = new ToLilypond();
                        break;
                    case "PDF File":
                        AppProperties.addLocalPropertiesBundle();
                        if (StringUtil.isEmpty(AppProperties.getOptionalProperty("location.lilypond"))) {
                            InputRow lilypondTextInputRow = new InputRow();
                            lilypondTextInputRow.setInputType(InputType.LABEL);
                            lilypondTextInputRow.setBoldText(true);
                            lilypondTextInputRow.setValue("Set Lilypond Location in Set Properties to create PDF file");
                            addFormRow(lilypondTextInputRow);
                        } else {
                            toInput = new ToPdf();
                        }
                        break;
                }
                break;
        }

        if (fromInput != null && toInput != null) {
            displayDataInput(fromInput);
            displayDataInput(toInput);
            InputRow buttonInputRow = new InputRow();
            buttonInputRow.setInputType(InputType.BUTTON);
            buttonInputRow.setName("Submit");
            addFormRow(buttonInputRow);
        }

        clearStatusArea();

        taskForm.revalidate();
    }

    private void displayDataInput(DataInput dataInput) {
        String title = dataInput.getTitle();
        if (StringUtil.isNotEmpty(title)) {
            InputRow titleInputRow = new InputRow();
            titleInputRow.setInputType(InputType.LABEL);
            titleInputRow.setBoldText(true);
            titleInputRow.setValue(title);
            addFormRow(titleInputRow);
        }

        for (InputRow inputRow : dataInput.getInputRows()) {
            if (rowIndex < NUMBER_OF_ROWS) formElementTextLabels.get(rowIndex).setText(inputRow.getText());
            addFormRow(inputRow);
        }
    }

    private void addFormRow(InputRow inputRow) {
        if (rowIndex >= NUMBER_OF_ROWS) return;

        addFormElement(rightPanels.get(rowIndex), inputRow);
        rowIndex++;
    }

    private void addFormElement(JPanel panel, InputRow inputRow) {
        Component component = null;

        switch (inputRow.getInputType()) {
            case LABEL:
                JLabel label = addNewLabel(panel, inputRow.getValue());
                if (inputRow.isBoldText()) setLabelBoldFont(label, 16);
                break;
            case INPUT:
                JTextField smallTextField = new JTextField();
                smallTextField.setText(inputRow.getValue());
                panel.add(smallTextField, getConstraints());
                component = smallTextField;
                break;
            case PASSWORD:
                GridBagLayout layout = (GridBagLayout)panel.getLayout();
                GridBagConstraints constraints = layout.getConstraints(panel);
                constraints.gridwidth = 2;

                JPanel leftPanel = addNewPanel(panel, 0, 0, .60, .08);
                JPanel rightPanel = addNewPanel(panel, 0, 1, .40, .08);
                JPasswordField passwordField = new JPasswordField();
                passwordField.setText(inputRow.getValue());
                leftPanel.add(passwordField, getConstraints());

                showPassword = new JCheckBox();
                showPassword.setBackground(getBackgroundColor());
                showPassword.setText("Show Password: ");
                showPassword.setHorizontalTextPosition(SwingConstants.LEFT);
                showPassword.addItemListener(e -> {
                    if (showPassword.isSelected()) passwordField.setEchoChar((char) 0);
                    else passwordField.setEchoChar('*');
                });
                rightPanel.add(showPassword, getConstraints());

                component = passwordField;
                break;
            case INPUT_FILE:
                JFileChooser inputFileChooser = new JFileChooser();
                UIManager.put("FileChooser.readOnly", Boolean.TRUE);
                inputFileChooser.setControlButtonsAreShown(false);
                inputFileChooser.setAcceptAllFileFilterUsed(false);
                if (StringUtil.isNotEmpty(inputRow.getValue())) {
                    FileFilter inputFileFilter = new FileNameExtensionFilter(inputRow.getValue(), inputRow.getValue());
                    inputFileChooser.addChoosableFileFilter(inputFileFilter);
                }
                if (StringUtil.isNotEmpty(inputRow.getSelectedFilename()))
                    inputFileChooser.setSelectedFile(new File(inputRow.getSelectedFilename()));
                panel.add(inputFileChooser, getConstraints());
                component = inputFileChooser;
                break;
            case OUTPUT_DIRECTORY:
                JFileChooser outputFileChooser = new JFileChooser();
                UIManager.put("FileChooser.readOnly", Boolean.TRUE);
                outputFileChooser.setControlButtonsAreShown(false);
                outputFileChooser.setAcceptAllFileFilterUsed(false);
                outputFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                panel.add(outputFileChooser, getConstraints());
                component = outputFileChooser;
                break;
            case SELECTION:
                JComboBox selection = new JComboBox(inputRow.getSelectionList());
                selection.setSelectedItem(inputRow.getSelectedItem());
                selection.setBackground(getBackgroundColor());
                panel.add(selection, getConstraints());
                component = selection;
                break;
            case CHECKBOX:
                JCheckBox checkBox = new JCheckBox();
                checkBox.setBackground(getBackgroundColor());
                panel.add(checkBox, getConstraints());
                component = checkBox;
                break;
            case BUTTON:
                JButton button = new JButton();
                button.setBackground(getBackgroundColor());
                button.setText(inputRow.getName());
                panel.add(button, getConstraints());

                button.addActionListener(e -> {
                    clearStatusArea();

                    Runnable formRunnable = this::handleForm;
                    Thread formThread = new Thread(formRunnable);
                    formThread.start();
                });

                component = button;
                break;
        }

        if (component != null && StringUtil.isNotEmpty(inputRow.getName())) componentMap.put(inputRow.getName(), component);
    }

    private void handleForm() {
        MusicXmlTask musicXmlTask = null;
        SwingTaskInitializer swingTaskInitializer = new SwingTaskInitializer(componentMap);

        switch (selectedValue) {
            case "Set Properties":
                musicXmlTask = new SetPropertiesTask(swingTaskInitializer);
                break;
            case "Database Tasks":
                musicXmlTask = new DatabaseTask(swingTaskInitializer);
                break;
            case "Conversion Tasks":
                switch (fromSelectedValue) {
                    case "MusicXml File":
                        switch (toSelectedValue) {
                            case "Database Record":
                                musicXmlTask = new MusicXml2DbTask(swingTaskInitializer);
                                break;
                            case "Lilypond File":
                                musicXmlTask = new MusicXml2LyTask(swingTaskInitializer);
                                break;
                            case "PDF File":
                                musicXmlTask = new MusicXml2PdfTask(swingTaskInitializer);
                                break;
                        }
                        break;
                    case "Database Record":
                        switch (toSelectedValue) {
                            case "MusicXml File":
                                musicXmlTask = new Db2MusicXmlTask(swingTaskInitializer);
                                break;
                            case "Lilypond File":
                                musicXmlTask = new Db2LyTask(swingTaskInitializer);
                                break;
                            case "PDF File":
                                musicXmlTask = new Db2PdfTask(swingTaskInitializer);
                                break;
                        }
                        break;
                    case "Lilypond File":
                        switch (toSelectedValue) {
                            case "PDF File":
                                musicXmlTask = new Ly2PdfTask(swingTaskInitializer);
                                break;
                        }
                        break;
                }
                break;
        }


        try {
            if (musicXmlTask != null) musicXmlTask.execute();
            System.err.println("Task finished");
        } catch (TaskException e) {
            System.err.println(e.getMessage());
        }
    }

    private void setupStatusArea() {
        PrintStream statusPrintStream = new PrintStream(new SwingStatusOutput(statusTextArea));
        System.setErr(statusPrintStream);
        System.setOut(statusPrintStream);
    }

    private void clearStatusArea() {
        try {
            statusTextArea.getDocument().remove(0, statusTextArea.getDocument().getLength());
            statusTextArea.update(statusTextArea.getGraphics());
        } catch (BadLocationException e) {
            //
        }
    }

    private void setupFormatSelections() {
        fromFormat = new JComboBox();
        fromFormat.setBackground(new Color(-1));
        final DefaultComboBoxModel fromModel = new DefaultComboBoxModel();
        fromModel.addElement("");
        fromModel.addElement("MusicXml File");
        fromModel.addElement("Database Record");
        fromModel.addElement("Lilypond File");
        fromFormat.setModel(fromModel);
        fromFormat.addActionListener(e -> {
            String selection = (String) fromFormat.getSelectedItem();
            if (!selection.equals(fromSelectedValue)) {
                fromSelectedValue = selection;

                DefaultComboBoxModel toModel = (DefaultComboBoxModel) toFormat.getModel();
                toModel.removeAllElements();
                toModel.addElement("");
                switch (fromSelectedValue) {
                    case "MusicXml File":
                        toModel.addElement("Database Record");
                        toModel.addElement("Lilypond File");
                        toModel.addElement("PDF File");
                        break;
                    case "Database Record":
                        toModel.addElement("MusicXml File");
                        toModel.addElement("Lilypond File");
                        toModel.addElement("PDF File");
                        break;
                    case "Lilypond File":
                        toModel.addElement("PDF File");
                        break;
                }

                handleSelection();
            }
        });

        toFormat = new JComboBox();
        toFormat.setBackground(new Color(-1));
        final DefaultComboBoxModel toModel = new DefaultComboBoxModel();
        toModel.addElement("");
        toFormat.setModel(toModel);
        toFormat.addActionListener(e -> {
            String selection = (String) toFormat.getSelectedItem();
            if (selection != null && !selection.equals(toSelectedValue)) {
                toSelectedValue = selection;
                handleSelection();
            }
        });
    }

    private void setupFormElements() {
        formElementTextLabels.add(formElement1Text);
        formElementTextLabels.add(formElement2Text);
        formElementTextLabels.add(formElement3Text);
        formElementTextLabels.add(formElement4Text);
        formElementTextLabels.add(formElement5Text);
        formElementTextLabels.add(formElement6Text);
        formElementTextLabels.add(formElement7Text);
        formElementTextLabels.add(formElement8Text);
        rightPanels.add(row3Right);
        rightPanels.add(row4Right);
        rightPanels.add(row5Right);
        rightPanels.add(row6Right);
        rightPanels.add(row7Right);
        rightPanels.add(row8Right);
        rightPanels.add(row9Right);
        rightPanels.add(row10Right);
    }

    private void resetFormElements() {
        for (int index = 0; index < NUMBER_OF_ROWS; index++) {
            formElementTextLabels.get(index).setText("");
            JPanel panel = rightPanels.get(index);
            panel.removeAll();
            GridBagLayout layout = (GridBagLayout)panel.getLayout();
            layout.setConstraints(panel, getConstraints());
        }
    }

    private void addMenuItems(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu tasksMenu = new JMenu("Tasks");
        addMenuItem(tasksMenu, "Set Properties");
        addMenuItem(tasksMenu, "Database Tasks");
        addMenuItem(tasksMenu, "Conversion Tasks");
        tasksMenu.addSeparator();
        addMenuItem(tasksMenu, "Exit");
        menuBar.add(tasksMenu);
        frame.setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenu menu, String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            JMenuItem actionMenuItem = (JMenuItem) e.getSource();
            String selection = actionMenuItem.getText();
            if (!selection.equals(selectedValue)) {
                if (selection.equals("Exit")) System.exit(0);
                else {
                    selectedValue = selection;
                    handleSelection();
                }
            }
        });
    }

    private JPanel addNewPanel(JComponent parentComponent, int rowNumber, int columnNumber, double weightx, double weighty) {
        return addNewPanel(parentComponent, rowNumber, columnNumber, 1, 1, weightx, weighty);
    }

    private JPanel addNewPanel(JComponent parentComponent, int rowNumber, int columnNumber,int gridHeight, int gridWidth, double weightx, double weighty) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(getBackgroundColor());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        if (parentComponent != null) parentComponent.add(panel, getConstraints(rowNumber, columnNumber, gridHeight, gridWidth, weightx, weighty));

        return panel;
    }

    private JLabel addNewLabel(JComponent parentComponent) {
        return addNewLabel(parentComponent, "");
    }

    private JLabel addNewLabel(JComponent parentComponent, String text) {
        JLabel label = new JLabel();
        label.setBackground(getBackgroundColor());
        label.setText(text);
        parentComponent.add(label);

        return label;
    }

    private void setLabelBoldFont(JLabel label, int size) {
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, size));
    }

    private JScrollPane addNewScrollPane(JComponent parentComponent) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(getBackgroundColor());
        parentComponent.add(scrollPane, getConstraints());

        return scrollPane;
    }

    private GridBagConstraints getConstraints() {
        return getConstraints(0, 0, 1, 1, 1, 1);
    }

    private GridBagConstraints getConstraints(int rowNumber, int columnNumber,int gridHeight, int gridWidth, double weightx, double weighty) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = columnNumber;
        constraints.gridy = rowNumber;
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MusicXmlTasks");
        MusicXmlTasks musicXmlTasks = new MusicXmlTasks();
        musicXmlTasks.addMenuItems(frame);
        frame.setContentPane(musicXmlTasks.taskForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 1000);
        frame.setVisible(true);
    }

    {
        setupUI();
    }

    private void setupUI() {
        taskForm = addNewPanel(null, 0, 0, 11, 2, 1, 1);
        header = addNewPanel(taskForm, 0, 0, 1, 2, .40, .08);
        headerLabel = addNewLabel(header, "MusicXml Tasks");
        setLabelBoldFont(headerLabel, 20);
        convertLabelPanel = addNewPanel(taskForm, 1, 0, .40, .08);
        convertLabel = addNewLabel(convertLabelPanel);
        convertPanel = addNewPanel(taskForm, 1, 1, 1, 3, .60, .08);
        convertFromPanel = addNewPanel(convertPanel, 0, 0, .40, .08);
        convertArrowPanel = addNewPanel(convertPanel, 0, 1, .20, .08);
        convertArrowLabel = addNewLabel(convertArrowPanel);
        convertToPanel = addNewPanel(convertPanel, 0, 2, .40, .08);
        row3Left = addNewPanel(taskForm, 2, 0, .40, .08);
        formElement1Text = addNewLabel(row3Left);
        row3Right = addNewPanel(taskForm, 2, 1, .60, .08);
        row4Left = addNewPanel(taskForm, 3, 0, .40, .08);
        formElement2Text = addNewLabel(row4Left);
        row4Right = addNewPanel(taskForm, 3, 1, .60, .08);
        row5Left = addNewPanel(taskForm, 4, 0, .40, .08);
        formElement3Text = addNewLabel(row5Left);
        row5Right = addNewPanel(taskForm, 4, 1, .60, .08);
        row6Left = addNewPanel(taskForm, 5, 0, .40, .08);
        formElement4Text = addNewLabel(row6Left);
        row6Right = addNewPanel(taskForm, 5, 1, .60, .08);
        row7Left = addNewPanel(taskForm, 6, 0, .40, .08);
        formElement5Text = addNewLabel(row7Left);
        row7Right = addNewPanel(taskForm, 6, 1, .60, .08);
        row8Left = addNewPanel(taskForm, 7, 0, .40, .08);
        formElement6Text = addNewLabel(row8Left);
        row8Right = addNewPanel(taskForm, 7, 1, .60, .08);
        row9Left = addNewPanel(taskForm, 8, 0, .40, .08);
        formElement7Text = addNewLabel(row9Left);
        row9Right = addNewPanel(taskForm, 8, 1, .60, .08);
        row10Left = addNewPanel(taskForm, 9, 0, .40, .08);
        formElement8Text = addNewLabel(row10Left);
        row10Right = addNewPanel(taskForm, 9, 1, .60, .08);
        statusPanel = addNewPanel(taskForm, 10, 0, 1, 2, 1, .20);
        statusScrollPane = addNewScrollPane(statusPanel);
        statusScrollPane.setVerticalScrollBarPolicy(22);
        statusTextArea = new JTextArea();
        statusTextArea.setBackground(getBackgroundColor());
        statusTextArea.setEditable(false);
        statusTextArea.setRows(8);
        statusTextArea.setText("");
        statusScrollPane.setViewportView(statusTextArea);
    }
}
