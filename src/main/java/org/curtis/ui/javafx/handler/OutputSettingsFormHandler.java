package org.curtis.ui.javafx.handler;

import org.curtis.properties.PropertiesHandler;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;

public class OutputSettingsFormHandler extends FormHandler {
    public OutputSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        PropertiesHandler.addLocalPropertiesBundle();

        taskForm.setSelectList(FormNode.TASK_OUTPUT_TYPE, FormNode.OUTPUT_TYPES);
        taskForm.setSelectList(FormNode.SQL_OUTPUT_TYPE, FormNode.OUTPUT_TYPES);
    }
}
