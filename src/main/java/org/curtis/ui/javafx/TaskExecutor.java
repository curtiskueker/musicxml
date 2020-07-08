package org.curtis.ui.javafx;

import javafx.application.Platform;
import org.curtis.properties.PropertiesHandler;
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
import org.curtis.ui.input.SetOutputPropertiesHandler;
import org.curtis.ui.input.ValidateXmlHandler;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.ui.javafx.handler.ConvertFormHandler;
import org.curtis.ui.javafx.handler.DbSettingsFormHandler;
import org.curtis.ui.javafx.handler.EmptyFormHandler;
import org.curtis.ui.javafx.handler.FormHandler;
import org.curtis.ui.javafx.handler.LyPdfSettingsFormHandler;
import org.curtis.ui.javafx.handler.OutputSettingsFormHandler;
import org.curtis.ui.javafx.initialize.ConvertInitializer;
import org.curtis.ui.javafx.initialize.DbTablesInitializer;
import org.curtis.ui.javafx.initialize.DeleteScoreInitializer;
import org.curtis.ui.javafx.initialize.JavafxTaskInitializer;
import org.curtis.ui.javafx.initialize.LyPdfSettingsInitializer;
import org.curtis.ui.javafx.initialize.OutputSettingsInitializer;
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
            case FormNode.DB_SETTINGS_TAB:
                formHandler = new DbSettingsFormHandler(taskForm);
                break;
            case FormNode.LY_PDF_SETTINGS_TAB:
                formHandler = new LyPdfSettingsFormHandler(taskForm);
                break;
            case FormNode.OUTPUT_SETTINGS_TAB:
                formHandler = new OutputSettingsFormHandler(taskForm);
                break;
            case FormNode.DB_ACTIONS_TAB:
            case FormNode.VALIDATE_TAB:
                formHandler = new EmptyFormHandler(taskForm);
                break;
            case FormNode.CONVERT_TAB:
                formHandler = new ConvertFormHandler(taskForm);
                break;
        }

        if (formHandler != null) formHandler.initializeForm();
    }

    public void execute() {
        InputHandler inputHandler = null;
        JavafxTaskInitializer taskInitializer = null;
        boolean isPropertiesSettingsUpdate = false;
        boolean isLyPdfPropertiesUpdate = false;
        boolean isDbUpdate = false;
        boolean isOutputSettingsUpdate = false;
        switch (controlId) {
            case FormNode.SAVE_DB_SETTINGS_BUTTON:
                taskInitializer = new SaveDbSettingsInitializer(taskForm);
                inputHandler = new SetDbPropertiesHandler();
                isPropertiesSettingsUpdate = true;
                break;
            case FormNode.SAVE_LY_PDF_SETTINGS_BUTTON:
                taskInitializer = new LyPdfSettingsInitializer(taskForm);
                inputHandler = new SetLyPdfPropertiesHandler();
                isPropertiesSettingsUpdate = true;
                isLyPdfPropertiesUpdate = true;
                break;
            case FormNode.SAVE_OUTPUT_SETTINGS_BUTTON:
                taskInitializer = new OutputSettingsInitializer(taskForm);
                inputHandler = new SetOutputPropertiesHandler();
                isPropertiesSettingsUpdate = true;
                isOutputSettingsUpdate = true;
                break;
            case FormNode.EXECUTE_DB_ACTIONS_BUTTON:
                taskInitializer = new DbTablesInitializer(taskForm);
                inputHandler = new DatabaseHandler();
                break;
            case FormNode.EXECUTE_VALIDATE_BUTTON:
                taskInitializer = new ValidateXmlInitializer(taskForm);
                inputHandler = new ValidateXmlHandler();
                break;
            case FormNode.EXECUTE_CONVERT_BUTTON:
                taskInitializer = new ConvertInitializer(taskForm);
                switch (taskForm.getFromSelection()) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        switch (taskForm.getToSelection()) {
                            case TaskConstants.CONVERSION_TYPE_DATABASE:
                                inputHandler = new MusicXml2DbHandler();
                                isDbUpdate = true;
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
            case FormNode.EXECUTE_DELETE_BUTTON:
                taskInitializer = new DeleteScoreInitializer(taskForm);
                inputHandler = new DeleteScoreHandler();
                isDbUpdate = true;
                break;
        }

        try {
            if (taskInitializer != null && inputHandler != null) {
                taskForm.disableNodes(true);
                taskInitializer.initializeNodeMap();
                MusicXmlTask musicXmlTask = new MusicXmlTask(taskInitializer, inputHandler);
                musicXmlTask.execute();

                if (isPropertiesSettingsUpdate) Platform.runLater(PropertiesHandler::addLocalPropertiesBundle);
                if (isLyPdfPropertiesUpdate) Platform.runLater(() -> taskForm.handleLyPdfUpdates());
                if (isDbUpdate) Platform.runLater(() -> {taskForm.handleDisplayUpdates();});
                if (isOutputSettingsUpdate) Platform.runLater(() -> taskForm.resetOutputStream());
            }
            System.err.println("Task finished successfully");
        } catch (TaskException e) {
            System.err.println("Task exited with exception");
            System.err.println(e.getMessage());
        } finally {
            taskForm.disableNodes(false);
        }
    }
}
