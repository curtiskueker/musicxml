package org.curtis.ui.javafx.handler;

import org.curtis.ui.javafx.form.TaskForm;

public abstract class FormHandler {
    protected TaskForm taskForm;

    public FormHandler(TaskForm taskForm) {
        this.taskForm = taskForm;
    }

    public abstract void initializeForm();
}
