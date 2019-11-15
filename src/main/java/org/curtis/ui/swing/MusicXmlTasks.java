package org.curtis.ui.swing;

import org.curtis.properties.AppProperties;
import org.curtis.ui.swing.handler.ComponentHandler;
import org.curtis.ui.swing.handler.TaskHandler;
import org.curtis.ui.swing.input.InputPanel;
import org.curtis.ui.swing.input.InputRowFactory;
import org.curtis.ui.task.TaskConstants;
import org.curtis.ui.swing.component.ComponentFactory;
import org.curtis.ui.swing.component.ConstraintsFactory;
import org.curtis.ui.swing.input.DatabaseInput;
import org.curtis.ui.swing.input.DatabaseOutput;
import org.curtis.ui.swing.input.FromDatabase;
import org.curtis.ui.swing.input.FromInput;
import org.curtis.ui.swing.input.FromLilypond;
import org.curtis.ui.swing.input.FromMusicXml;
import org.curtis.ui.swing.input.PropertiesInput;
import org.curtis.ui.swing.input.PropertiesOutput;
import org.curtis.ui.swing.input.ToDatabase;
import org.curtis.ui.swing.input.ToInput;
import org.curtis.ui.swing.input.ToLilypond;
import org.curtis.ui.swing.input.ToMusicXml;
import org.curtis.ui.swing.input.ToPdf;
import org.curtis.util.StringUtil;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private String convertFromSelection = "";
    private String convertToSelection = "";
    private JComboBox fromFormat;
    private JComboBox toFormat;

    private String menuSelection;

    private List<InputPanel> inputPanels = new ArrayList<>();
    private ComponentHandler componentHandler = new ComponentHandler(inputPanels);

    public static final double HORIZONTAL_SMALL_WEIGHT = .40;
    public static final double HORIZONTAL_LARGE_WEIGHT = .60;
    public static final double VERTICAL_CELL_WEIGHT = .08;
    public static final double VERTICAL_STATUS_WEIGHT = .20;

    public MusicXmlTasks() {
        setupStatusArea();
        setupFormatSelections();
        setupFormElements();
    }

    private void handleSelection() {
        componentHandler.resetFormElements();

        convertFromPanel.removeAll();
        convertToPanel.removeAll();
        componentHandler = new ComponentHandler(inputPanels);

        headerLabel.setText(menuSelection);

        if (menuSelection.equals(TaskConstants.MENU_CONVERSION_TASKS)) {
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
            case TaskConstants.MENU_SET_PROPERTIES:
                fromInput = new PropertiesInput();
                toInput = new PropertiesOutput();
                break;
            case TaskConstants.MENU_DATABASE_TASKS:
                fromInput = new DatabaseInput();
                toInput = new DatabaseOutput();
                break;
            case TaskConstants.MENU_CONVERSION_TASKS:
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
                        AppProperties.addLocalPropertiesBundle();
                        if (StringUtil.isEmpty(AppProperties.getOptionalProperty("location.lilypond")))
                            componentHandler.addFormRow(InputRowFactory.newBoldLabel("Set Lilypond Location in Set Properties to create PDF file"));
                        else toInput = new ToPdf();
                        break;
                }
                break;
        }

        componentHandler.displayData(fromInput, toInput);
        clearStatusArea();

        Component submitButton = componentHandler.getComponentMap().get(TaskConstants.SUBMIT_BUTTON);
        if (submitButton != null) {
            JButton button = (JButton)submitButton;
            button.addActionListener(e -> {
                clearStatusArea();

                Runnable formRunnable = this::handleForm;
                Thread formThread = new Thread(formRunnable);
                formThread.start();
            });
        }

        taskForm.revalidate();
    }

    private void handleForm() {
        TaskHandler.handleTask(componentHandler.getComponentMap(), menuSelection, convertFromSelection, convertToSelection);
    }

    private void setupStatusArea() {
        PrintStream statusPrintStream = new PrintStream(new StatusOutput(statusTextArea));
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
        fromFormat = ComponentFactory.newComboBox(Arrays.asList(TaskConstants.CONVERSION_TYPE_MUSICXML, TaskConstants.CONVERSION_TYPE_DATABASE, TaskConstants.CONVERSION_TYPE_LILYPOND));
        fromFormat.addActionListener(e -> {
            String selection = (String) fromFormat.getSelectedItem();
            if (!selection.equals(convertFromSelection)) {
                convertFromSelection = selection;

                DefaultComboBoxModel toModel = (DefaultComboBoxModel) toFormat.getModel();
                toModel.removeAllElements();
                toModel.addElement("");
                switch (convertFromSelection) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        toModel.addElement(TaskConstants.CONVERSION_TYPE_DATABASE);
                        toModel.addElement(TaskConstants.CONVERSION_TYPE_LILYPOND);
                        toModel.addElement(TaskConstants.CONVERSION_TYPE_PDF);
                        break;
                    case TaskConstants.CONVERSION_TYPE_DATABASE:
                        toModel.addElement(TaskConstants.CONVERSION_TYPE_MUSICXML);
                        toModel.addElement(TaskConstants.CONVERSION_TYPE_LILYPOND);
                        toModel.addElement(TaskConstants.CONVERSION_TYPE_PDF);
                        break;
                    case TaskConstants.CONVERSION_TYPE_LILYPOND:
                        toModel.addElement(TaskConstants.CONVERSION_TYPE_PDF);
                        break;
                }

                handleSelection();
            }
        });

        toFormat = ComponentFactory.newComboBox();
        toFormat.addActionListener(e -> {
            String selection = (String) toFormat.getSelectedItem();
            if (selection != null && !selection.equals(convertToSelection)) {
                convertToSelection = selection;
                handleSelection();
            }
        });
    }

    private void setupFormElements() {
        inputPanels.add(new InputPanel(formElement1Text, row3Right));
        inputPanels.add(new InputPanel(formElement2Text, row4Right));
        inputPanels.add(new InputPanel(formElement3Text, row5Right));
        inputPanels.add(new InputPanel(formElement4Text, row6Right));
        inputPanels.add(new InputPanel(formElement5Text, row7Right));
        inputPanels.add(new InputPanel(formElement6Text, row8Right));
        inputPanels.add(new InputPanel(formElement7Text, row9Right));
        inputPanels.add(new InputPanel(formElement8Text, row10Right));
    }

    private void addMenuItems(JFrame frame) {
        JMenuBar menuBar = ComponentFactory.newMenu(
                "Tasks",
                Arrays.asList(
                        TaskConstants.MENU_SET_PROPERTIES, TaskConstants.MENU_DATABASE_TASKS, TaskConstants.MENU_CONVERSION_TASKS,
                        TaskConstants.MENU_SEPARATOR,
                        TaskConstants.MENU_EXIT_APPLICATION
                )
        );

        for (int menuNumber = 0; menuNumber < menuBar.getMenuCount(); menuNumber++) {
            JMenu menu = menuBar.getMenu(menuNumber);
            for (int menuItemNumber = 0; menuItemNumber < menu.getItemCount(); menuItemNumber++) {
                JMenuItem menuItem = menu.getItem(menuItemNumber);
                if (menuItem == null) continue;

                menuItem.addActionListener(e -> {
                    JMenuItem actionMenuItem = (JMenuItem) e.getSource();
                    String selection = actionMenuItem.getText();
                    if (!selection.equals(menuSelection)) {
                        if (selection.equals(TaskConstants.MENU_EXIT_APPLICATION)) System.exit(0);
                        else {
                            menuSelection = selection;
                            handleSelection();
                        }
                    }
                });
            }
        }
        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(TaskConstants.TASKS_TITLE);
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
        taskForm = componentHandler.addNewPanel(null, 0, 0, 11, 2, 1, 1);
        header = componentHandler.addNewPanel(taskForm, 0, 0, 1, 2, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        headerLabel = ComponentFactory.newBoldLabel(TaskConstants.TASKS_TITLE, 20);
        header.add(headerLabel);
        convertLabelPanel = componentHandler.addNewPanel(taskForm, 1, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        convertLabel = componentHandler.addNewLabel(convertLabelPanel);
        convertPanel = componentHandler.addNewPanel(taskForm, 1, 1, 1, 3, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        convertFromPanel = componentHandler.addNewPanel(convertPanel, 0, 0, .40, VERTICAL_CELL_WEIGHT);
        convertArrowPanel = componentHandler.addNewPanel(convertPanel, 0, 1, .20, VERTICAL_CELL_WEIGHT);
        convertArrowLabel = componentHandler.addNewLabel(convertArrowPanel);
        convertToPanel = componentHandler.addNewPanel(convertPanel, 0, 2, .40, VERTICAL_CELL_WEIGHT);
        row3Left = componentHandler.addNewPanel(taskForm, 2, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        formElement1Text = componentHandler.addNewLabel(row3Left);
        row3Right = componentHandler.addNewPanel(taskForm, 2, 1, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        row4Left = componentHandler.addNewPanel(taskForm, 3, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        formElement2Text = componentHandler.addNewLabel(row4Left);
        row4Right = componentHandler.addNewPanel(taskForm, 3, 1, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        row5Left = componentHandler.addNewPanel(taskForm, 4, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        formElement3Text = componentHandler.addNewLabel(row5Left);
        row5Right = componentHandler.addNewPanel(taskForm, 4, 1, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        row6Left = componentHandler.addNewPanel(taskForm, 5, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        formElement4Text = componentHandler.addNewLabel(row6Left);
        row6Right = componentHandler.addNewPanel(taskForm, 5, 1, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        row7Left = componentHandler.addNewPanel(taskForm, 6, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        formElement5Text = componentHandler.addNewLabel(row7Left);
        row7Right = componentHandler.addNewPanel(taskForm, 6, 1, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        row8Left = componentHandler.addNewPanel(taskForm, 7, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        formElement6Text = componentHandler.addNewLabel(row8Left);
        row8Right = componentHandler.addNewPanel(taskForm, 7, 1, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        row9Left = componentHandler.addNewPanel(taskForm, 8, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        formElement7Text = componentHandler.addNewLabel(row9Left);
        row9Right = componentHandler.addNewPanel(taskForm, 8, 1, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        row10Left = componentHandler.addNewPanel(taskForm, 9, 0, HORIZONTAL_SMALL_WEIGHT, VERTICAL_CELL_WEIGHT);
        formElement8Text = componentHandler.addNewLabel(row10Left);
        row10Right = componentHandler.addNewPanel(taskForm, 9, 1, HORIZONTAL_LARGE_WEIGHT, VERTICAL_CELL_WEIGHT);
        statusPanel = ComponentFactory.newPanel();
        GridBagConstraints statusPanelConstraints = ConstraintsFactory.getNewConstraints(10, 0, 1, 2, 1, VERTICAL_STATUS_WEIGHT, GridBagConstraints.BOTH, GridBagConstraints.PAGE_END);
        componentHandler.addComponent(taskForm, statusPanel, statusPanelConstraints);
        statusScrollPane = componentHandler.addNewScrollPane(statusPanel);
        statusTextArea = new JTextArea();
        statusTextArea.setBackground(ComponentFactory.getBackgroundColor());
        statusTextArea.setEditable(false);
        statusTextArea.setRows(8);
        statusTextArea.setText("");
        statusScrollPane.setViewportView(statusTextArea);
    }
}
