package org.curtis.ui.javafx;

import javafx.application.Platform;
import org.curtis.properties.AppProperties;
import org.curtis.ui.input.DatabaseHandler;
import org.curtis.ui.input.Db2LyHandler;
import org.curtis.ui.input.Db2MusicXmlHandler;
import org.curtis.ui.input.Db2PdfHandler;
import org.curtis.ui.input.DeleteScoreHandler;
import org.curtis.ui.input.InputHandler;
import org.curtis.ui.input.Ly2PdfHandler;
import org.curtis.ui.input.MusicXml2DbHandler;
import org.curtis.ui.input.MusicXml2LyHandler;
import org.curtis.ui.input.MusicXml2PdfHandler;
import org.curtis.ui.input.SetDbPropertiesHandler;
import org.curtis.ui.input.SetLyPdfPropertiesHandler;
import org.curtis.ui.input.ValidateXmlHandler;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.ui.javafx.handler.ConvertFormHandler;
import org.curtis.ui.javafx.handler.DbSettingsFormHandler;
import org.curtis.ui.javafx.handler.EmptyFormHandler;
import org.curtis.ui.javafx.handler.FormHandler;
import org.curtis.ui.javafx.handler.LyPdfSettingsFormHandler;
import org.curtis.ui.javafx.initialize.ConvertInitializer;
import org.curtis.ui.javafx.initialize.DbTablesInitializer;
import org.curtis.ui.javafx.initialize.DeleteScoreInitializer;
import org.curtis.ui.javafx.initialize.JavafxTaskInitializer;
import org.curtis.ui.javafx.initialize.LyPdfSettingsInitializer;
import org.curtis.ui.javafx.initialize.SaveDbSettingsInitializer;
import org.curtis.ui.javafx.initialize.ValidateXmlInitializer;
import org.curtis.ui.task.MusicXmlTask;
import org.curtis.ui.task.TaskConstants;
import org.curtis.ui.task.TaskException;

public class TaskExecutor {
    private String controlId;
    private TaskForm taskForm;

    public TaskExecutor(String controlId, TaskForm taskForm) {
        this.controlId = controlId;
        this.taskForm = taskForm;
    }

    public void initializeForm() {
        FormHandler formHandler = null;
        switch (controlId) {
            case "settingsTab":
                formHandler = new DbSettingsFormHandler(taskForm);
                break;
            case "lyPdfTab":
                formHandler = new LyPdfSettingsFormHandler(taskForm);
                break;
            case "tablesTab":
            case "validateTab":
                formHandler = new EmptyFormHandler(taskForm);
                break;
            case "convertTab":
                formHandler = new ConvertFormHandler(taskForm);
                break;
        }

        if (formHandler != null) formHandler.initializeForm();
    }

    public void execute() {
        InputHandler inputHandler = null;
        JavafxTaskInitializer taskInitializer = null;
        switch (controlId) {
            case "saveSettingsButton":
                taskInitializer = new SaveDbSettingsInitializer(taskForm);
                inputHandler = new SetDbPropertiesHandler();
                break;
            case "executeLyPdf":
                taskInitializer = new LyPdfSettingsInitializer(taskForm);
                inputHandler = new SetLyPdfPropertiesHandler();
                break;
            case "executeTables":
                taskInitializer = new DbTablesInitializer(taskForm);
                inputHandler = new DatabaseHandler();
                break;
            case "executeValidate":
                taskInitializer = new ValidateXmlInitializer(taskForm);
                inputHandler = new ValidateXmlHandler();
                break;
            case "executeConvert":
                taskInitializer = new ConvertInitializer(taskForm);
                switch (taskForm.getFromSelection()) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        switch (taskForm.getToSelection()) {
                            case TaskConstants.CONVERSION_TYPE_DATABASE:
                                inputHandler = new MusicXml2DbHandler();
                                break;
                            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                                inputHandler = new MusicXml2LyHandler();
                                break;
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                inputHandler = new MusicXml2PdfHandler();
                                break;
                        }
                        break;
                    case TaskConstants.CONVERSION_TYPE_DATABASE:
                        switch (taskForm.getToSelection()) {
                            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                                inputHandler = new Db2MusicXmlHandler();
                                break;
                            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                                inputHandler = new Db2LyHandler();
                                break;
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                inputHandler = new Db2PdfHandler();
                                break;
                        }
                        break;
                    case TaskConstants.CONVERSION_TYPE_LILYPOND:
                        if (TaskConstants.CONVERSION_TYPE_PDF.equals(taskForm.getToSelection())) {
                            inputHandler = new Ly2PdfHandler();
                        }
                        break;
                }
                break;
            case "executeDelete":
                taskInitializer = new DeleteScoreInitializer(taskForm);
                inputHandler = new DeleteScoreHandler();
                break;
        }

        try {
            if (taskInitializer != null && inputHandler != null) {
                taskInitializer.initializeNodeMap();
                MusicXmlTask musicXmlTask = new MusicXmlTask(taskInitializer, inputHandler);
                musicXmlTask.execute();

                Platform.runLater(() -> {
                    AppProperties.addLocalPropertiesBundle();
                    taskForm.handleDisplayUpdates();
                });
            }
            System.err.println("Task finished");
        } catch (TaskException e) {
            System.err.println(e.getMessage());
        }
    }
}
