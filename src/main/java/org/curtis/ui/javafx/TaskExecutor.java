package org.curtis.ui.javafx;

import org.curtis.ui.javafx.form.DbSettingsFormHandler;
import org.curtis.ui.javafx.form.FormHandler;
import org.curtis.ui.javafx.initialize.JavafxTaskInitializer;
import org.curtis.ui.javafx.initialize.SaveDbSettingsInitializer;
import org.curtis.ui.task.MusicXmlTask;
import org.curtis.ui.task.SetDbPropertiesTask;
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
        }

        try {
            if (taskInitializer != null) taskInitializer.initializeNodeMap();
            if (musicXmlTask != null) musicXmlTask.execute();
            System.err.println("Task finished");
        } catch (TaskException e) {
            System.err.println(e.getMessage());
        }

        tasksController.getStatusOutput().flushOutput();
    }
}
