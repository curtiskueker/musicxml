package org.curtis.ui.swing;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
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
            convertFromPanel.add(fromSelection, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

            JComboBox toSelection = toFormat;
            toSelection.setSelectedItem(toSelectedValue);
            convertToPanel.add(toSelection, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
                JLabel label = new JLabel();
                if (inputRow.isBoldText()) label.setFont(new Font(headerLabel.getFont().getName(), Font.BOLD, 16));
                label.setText(inputRow.getValue());
                panel.add(label, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
                break;
            case INPUT:
                JTextField smallTextField = new JTextField();
                smallTextField.setText(inputRow.getValue());
                panel.add(smallTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(inputRow.getInputSize(), -1), new Dimension(inputRow.getInputSize(), -1), new Dimension(inputRow.getInputSize(), -1), 0, false));
                component = smallTextField;
                break;
            case PASSWORD:
                panel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));

                JPanel leftPanel = new JPanel();
                leftPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
                leftPanel.setBackground(new Color(-1));
                panel.add(leftPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

                JPanel rightPanel = new JPanel();
                rightPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
                rightPanel.setBackground(new Color(-1));
                panel.add(rightPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

                JPasswordField passwordField = new JPasswordField();
                passwordField.setText(inputRow.getValue());
                leftPanel.add(passwordField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(inputRow.getInputSize(), -1), new Dimension(inputRow.getInputSize(), -1), new Dimension(inputRow.getInputSize(), -1), 0, false));

                showPassword = new JCheckBox();
                showPassword.setBackground(new Color(-1));
                showPassword.setText("Show Password: ");
                showPassword.setHorizontalTextPosition(SwingConstants.LEFT);
                showPassword.addItemListener(e -> {
                    if (showPassword.isSelected()) passwordField.setEchoChar((char) 0);
                    else passwordField.setEchoChar('*');
                });
                rightPanel.add(showPassword, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

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
                panel.add(inputFileChooser, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(CHOOSER_SIZE, -1), new Dimension(CHOOSER_SIZE, -1), new Dimension(CHOOSER_SIZE, -1), 0, false));
                component = inputFileChooser;
                break;
            case OUTPUT_DIRECTORY:
                JFileChooser outputFileChooser = new JFileChooser();
                UIManager.put("FileChooser.readOnly", Boolean.TRUE);
                outputFileChooser.setControlButtonsAreShown(false);
                outputFileChooser.setAcceptAllFileFilterUsed(false);
                outputFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                panel.add(outputFileChooser, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(CHOOSER_SIZE, -1), new Dimension(CHOOSER_SIZE, -1), new Dimension(CHOOSER_SIZE, -1), 0, false));
                component = outputFileChooser;
                break;
            case SELECTION:
                JComboBox selection = new JComboBox(inputRow.getSelectionList());
                selection.setSelectedItem(inputRow.getSelectedItem());
                selection.setBackground(new Color(-1));
                panel.add(selection, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
                component = selection;
                break;
            case CHECKBOX:
                JCheckBox checkBox = new JCheckBox();
                checkBox.setBackground(new Color(-1));
                panel.add(checkBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
                component = checkBox;
                break;
            case BUTTON:
                JButton button = new JButton();
                button.setBackground(new Color(-1));
                button.setText(inputRow.getName());
                panel.add(button, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

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
            panel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
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
        taskForm = new JPanel();
        taskForm.setLayout(new GridLayoutManager(11, 2, new Insets(0, 0, 0, 0), -1, -1));
        taskForm.setBackground(new Color(-1));
        header = new JPanel();
        header.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        header.setBackground(new Color(-1));
        taskForm.add(header, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        headerLabel = new JLabel();
        headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.BOLD, 20));
        headerLabel.setText("MusicXml Tasks");
        header.add(headerLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row3Left = new JPanel();
        row3Left.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row3Left.setBackground(new Color(-1));
        taskForm.add(row3Left, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formElement1Text = new JLabel();
        formElement1Text.setBackground(new Color(-1));
        formElement1Text.setText("");
        row3Left.add(formElement1Text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row3Right = new JPanel();
        row3Right.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row3Right.setBackground(new Color(-1));
        taskForm.add(row3Right, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        row4Left = new JPanel();
        row4Left.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row4Left.setBackground(new Color(-1));
        taskForm.add(row4Left, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formElement2Text = new JLabel();
        formElement2Text.setBackground(new Color(-1));
        formElement2Text.setText("");
        row4Left.add(formElement2Text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row4Right = new JPanel();
        row4Right.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row4Right.setBackground(new Color(-1));
        taskForm.add(row4Right, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        row5Left = new JPanel();
        row5Left.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row5Left.setBackground(new Color(-1));
        taskForm.add(row5Left, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formElement3Text = new JLabel();
        formElement3Text.setBackground(new Color(-1));
        formElement3Text.setText("");
        row5Left.add(formElement3Text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row5Right = new JPanel();
        row5Right.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row5Right.setBackground(new Color(-1));
        taskForm.add(row5Right, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        row6Left = new JPanel();
        row6Left.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row6Left.setBackground(new Color(-1));
        taskForm.add(row6Left, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formElement4Text = new JLabel();
        formElement4Text.setBackground(new Color(-1));
        formElement4Text.setText("");
        row6Left.add(formElement4Text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row6Right = new JPanel();
        row6Right.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row6Right.setBackground(new Color(-1));
        taskForm.add(row6Right, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        row7Left = new JPanel();
        row7Left.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row7Left.setBackground(new Color(-1));
        taskForm.add(row7Left, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formElement5Text = new JLabel();
        formElement5Text.setBackground(new Color(-1));
        formElement5Text.setText("");
        row7Left.add(formElement5Text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row7Right = new JPanel();
        row7Right.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row7Right.setBackground(new Color(-1));
        taskForm.add(row7Right, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        row8Left = new JPanel();
        row8Left.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row8Left.setBackground(new Color(-1));
        taskForm.add(row8Left, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formElement6Text = new JLabel();
        formElement6Text.setBackground(new Color(-1));
        formElement6Text.setText("");
        row8Left.add(formElement6Text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row8Right = new JPanel();
        row8Right.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row8Right.setBackground(new Color(-1));
        taskForm.add(row8Right, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        statusPanel.setBackground(new Color(-1));
        taskForm.add(statusPanel, new GridConstraints(10, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        statusScrollPane = new JScrollPane();
        statusScrollPane.setBackground(new Color(-1));
        statusScrollPane.setVerticalScrollBarPolicy(22);
        statusPanel.add(statusScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        statusTextArea = new JTextArea();
        statusTextArea.setEditable(false);
        statusTextArea.setRows(8);
        statusTextArea.setText("");
        statusScrollPane.setViewportView(statusTextArea);
        row9Left = new JPanel();
        row9Left.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row9Left.setBackground(new Color(-1));
        taskForm.add(row9Left, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formElement7Text = new JLabel();
        formElement7Text.setBackground(new Color(-1));
        formElement7Text.setText("");
        row9Left.add(formElement7Text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row9Right = new JPanel();
        row9Right.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row9Right.setBackground(new Color(-1));
        taskForm.add(row9Right, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        convertLabelPanel = new JPanel();
        convertLabelPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        convertLabelPanel.setBackground(new Color(-1));
        taskForm.add(convertLabelPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        convertLabel = new JLabel();
        convertLabel.setBackground(new Color(-1));
        convertLabel.setText("");
        convertLabelPanel.add(convertLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        convertPanel = new JPanel();
        convertPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        convertPanel.setBackground(new Color(-1));
        taskForm.add(convertPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        convertFromPanel = new JPanel();
        convertFromPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        convertFromPanel.setBackground(new Color(-1));
        convertPanel.add(convertFromPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        convertArrowPanel = new JPanel();
        convertArrowPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        convertArrowPanel.setBackground(new Color(-1));
        convertPanel.add(convertArrowPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        convertArrowLabel = new JLabel();
        convertArrowLabel.setBackground(new Color(-1));
        convertArrowLabel.setText("");
        convertArrowPanel.add(convertArrowLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        convertToPanel = new JPanel();
        convertToPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        convertToPanel.setBackground(new Color(-1));
        convertPanel.add(convertToPanel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        row10Left = new JPanel();
        row10Left.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row10Left.setBackground(new Color(-1));
        taskForm.add(row10Left, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formElement8Text = new JLabel();
        formElement8Text.setBackground(new Color(-1));
        formElement8Text.setText("");
        row10Left.add(formElement8Text, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        row10Right = new JPanel();
        row10Right.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        row10Right.setBackground(new Color(-1));
        taskForm.add(row10Right, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }
}
