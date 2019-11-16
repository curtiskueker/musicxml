package org.curtis.ui.javafx;

import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.handler.ConvertFormHandler;
import org.curtis.ui.javafx.handler.DbSettingsFormHandler;
import org.curtis.ui.javafx.handler.EmptyFormHandler;
import org.curtis.ui.javafx.handler.FormHandler;
import org.curtis.ui.javafx.handler.LyPdfSettingsFormHandler;
import org.curtis.ui.javafx.initialize.ConvertInitializer;
import org.curtis.ui.javafx.initialize.DbTablesInitializer;
import org.curtis.ui.javafx.initialize.JavafxTaskInitializer;
import org.curtis.ui.javafx.initialize.LyPdfSettingsInitializer;
import org.curtis.ui.javafx.initialize.SaveDbSettingsInitializer;
import org.curtis.ui.javafx.initialize.ValidateXmlInitializer;
import org.curtis.ui.task.DatabaseTask;
import org.curtis.ui.task.Db2LyTask;
import org.curtis.ui.task.Db2MusicXmlTask;
import org.curtis.ui.task.Db2PdfTask;
import org.curtis.ui.task.Ly2PdfTask;
import org.curtis.ui.task.MusicXml2DbTask;
import org.curtis.ui.task.MusicXml2LyTask;
import org.curtis.ui.task.MusicXml2PdfTask;
import org.curtis.ui.task.MusicXmlTask;
import org.curtis.ui.task.SetDbPropertiesTask;
import org.curtis.ui.task.SetLyPdfPropertiesTask;
import org.curtis.ui.task.TaskConstants;
import org.curtis.ui.task.ValidateXmlTask;
import org.curtis.ui.task.exception.TaskException;

public class TaskExecutor {
    private String controlId;
    private TasksController tasksController;

    public TaskExecutor(String controlId, TasksController tasksController) {
        this.controlId = controlId;
        this.tasksController = tasksController;
    }

    public void initializeForm() {
        FormHandler formHandler = null;
        switch (controlId) {
            case "settingsTab":
                formHandler = new DbSettingsFormHandler(tasksController);
                break;
            case "lyPdfTab":
                formHandler = new LyPdfSettingsFormHandler(tasksController);
                break;
            case "tablesTab":
            case "validateTab":
                formHandler = new EmptyFormHandler(tasksController);
                break;
            case "convertTab":
                formHandler = new ConvertFormHandler(tasksController);
                break;
        }

        if (formHandler != null) formHandler.initializeForm();
    }

    public void execute() {
        MusicXmlTask musicXmlTask = null;
        JavafxTaskInitializer taskInitializer = null;
        switch (controlId) {
            case "saveSettingsButton":
                taskInitializer = new SaveDbSettingsInitializer(tasksController);
                musicXmlTask = new SetDbPropertiesTask(taskInitializer);
                break;
            case "executeLyPdf":
                taskInitializer = new LyPdfSettingsInitializer(tasksController);
                musicXmlTask = new SetLyPdfPropertiesTask(taskInitializer);
                break;
            case "executeTables":
                taskInitializer = new DbTablesInitializer(tasksController);
                musicXmlTask = new DatabaseTask(taskInitializer);
                break;
            case "executeValidate":
                taskInitializer = new ValidateXmlInitializer(tasksController);
                musicXmlTask = new ValidateXmlTask(taskInitializer);
                break;
            case "executeConvert":
                taskInitializer = new ConvertInitializer(tasksController);
                switch (tasksController.getFromSelection()) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        switch (tasksController.getToSelection()) {
                            case TaskConstants.CONVERSION_TYPE_DATABASE:
                                musicXmlTask = new MusicXml2DbTask(taskInitializer);
                                break;
                            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                                musicXmlTask = new MusicXml2LyTask(taskInitializer);
                                break;
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                musicXmlTask = new MusicXml2PdfTask(taskInitializer);
                                break;
                        }
                        break;
                    case TaskConstants.CONVERSION_TYPE_DATABASE:
                        switch (tasksController.getToSelection()) {
                            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                                musicXmlTask = new Db2MusicXmlTask(taskInitializer);
                                break;
                            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                                musicXmlTask = new Db2LyTask(taskInitializer);
                                break;
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                musicXmlTask = new Db2PdfTask(taskInitializer);
                                break;
                        }
                        break;
                    case TaskConstants.CONVERSION_TYPE_LILYPOND:
                        switch (tasksController.getToSelection()) {
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                musicXmlTask = new Ly2PdfTask(taskInitializer);
                                break;
                        }
                        break;
                }
                break;
        }

        try {
            if (taskInitializer != null) taskInitializer.initializeNodeMap();
            if (musicXmlTask != null) {
                musicXmlTask.execute();
                AppProperties.addLocalPropertiesBundle();
                tasksController.handlePdfReaderDisplay();
            }
            System.err.println("Task finished");
        } catch (TaskException e) {
            System.err.println(e.getMessage());
        }
    }
}
