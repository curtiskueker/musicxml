package org.curtis.ui.swing;

import org.curtis.ui.swing.handler.FormHandler;
import org.curtis.ui.task.TaskConstants;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.util.Arrays;
import java.util.Collections;

public class MusicXmlTasks {
    private FormHandler formHandler;

    public MusicXmlTasks() {

    }

    private void execute() {
        formHandler = new FormHandler();

        JFrame frame = new JFrame(TaskConstants.TASKS_TITLE);
        addMenu(frame);
        setupFormatSelections();
        frame.setContentPane(formHandler.initializeForm());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 1000);
        frame.setVisible(true);
    }

    private void addMenu(JFrame frame) {
        JMenuBar menuBar = formHandler.setupMenu();

        for (int menuNumber = 0; menuNumber < menuBar.getMenuCount(); menuNumber++) {
            JMenu menu = menuBar.getMenu(menuNumber);
            for (int menuItemNumber = 0; menuItemNumber < menu.getItemCount(); menuItemNumber++) {
                JMenuItem menuItem = menu.getItem(menuItemNumber);
                if (menuItem == null) continue;

                menuItem.addActionListener(e -> {
                    JMenuItem actionMenuItem = (JMenuItem) e.getSource();
                    String selection = actionMenuItem.getText();
                    if (!selection.equals(formHandler.getMenuSelection())) {
                        if (selection.equals(TaskConstants.MENU_EXIT_APPLICATION)) System.exit(0);
                        else {
                            formHandler.setMenuSelection(selection);
                            handleSelection();
                        }
                    }
                });
            }
        }
        frame.setJMenuBar(menuBar);
    }

    private void setupFormatSelections() {
        JComboBox<String> fromFormat = formHandler.setupFromFormatSelection();
        fromFormat.addActionListener(e -> {
            String selection = (String) fromFormat.getSelectedItem();
            if (!selection.equals(formHandler.getConvertFromSelection())) {
                formHandler.setConvertFromSelection(selection);
                switch (formHandler.getConvertFromSelection()) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        formHandler.resetToFormatSelections(Arrays.asList(TaskConstants.CONVERSION_TYPE_DATABASE, TaskConstants.CONVERSION_TYPE_LILYPOND, TaskConstants.CONVERSION_TYPE_PDF));
                        break;
                    case TaskConstants.CONVERSION_TYPE_DATABASE:
                        formHandler.resetToFormatSelections(Arrays.asList(TaskConstants.CONVERSION_TYPE_MUSICXML, TaskConstants.CONVERSION_TYPE_LILYPOND, TaskConstants.CONVERSION_TYPE_PDF));
                        break;
                    case TaskConstants.CONVERSION_TYPE_LILYPOND:
                        formHandler.resetToFormatSelections(Collections.singletonList(TaskConstants.CONVERSION_TYPE_PDF));
                        break;
                }

                handleSelection();
            }
        });

        JComboBox<String> toFormat = formHandler.setupToFormatSelection();
        toFormat.addActionListener(e -> {
            String selection = (String) toFormat.getSelectedItem();
            if (selection != null && !selection.equals(formHandler.getConvertToSelection())) {
                formHandler.setConvertToSelection(selection);
                handleSelection();
            }
        });
    }

    private void handleSelection() {
        JButton submitButton = formHandler.handleSelection();
        if (submitButton != null) {
            submitButton.addActionListener(e -> {
                formHandler.clearStatusArea();

                Runnable formRunnable = this::handleForm;
                Thread formThread = new Thread(formRunnable);
                formThread.start();
            });
        }
    }

    private void handleForm() {
        formHandler.handleTask();
    }

    public static void main(String[] args) {
        MusicXmlTasks musicXmlTasks = new MusicXmlTasks();
        musicXmlTasks.execute();
    }
}
