package org.curtis.ui.javafx.handler;

import org.curtis.ui.javafx.TasksController;

public abstract class FormHandler {
    protected TasksController tasksController;

    public FormHandler(TasksController tasksController) {
        this.tasksController = tasksController;
    }

    public abstract void initializeForm();
}
