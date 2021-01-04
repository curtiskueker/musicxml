package org.curtis.ui.javafx.handler;

import org.curtis.properties.PropertiesConstants;
import org.curtis.properties.PropertiesHandler;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.ui.task.TaskConstants;

public class OutputSettingsFormHandler extends FormHandler {
    public OutputSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        PropertiesHandler.addLocalProperties();

        taskForm.setSelectList(FormNode.TASK_OUTPUT_TYPE, TaskConstants.OUTPUT_TYPES, PropertiesHandler.getOptionalProperty(PropertiesConstants.TASK_OUTPUT_TYPE));
        taskForm.getTextField(FormNode.TASK_OUTPUT_LOCATION).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.getDisplayProperty(PropertiesConstants.TASK_OUTPUT_LOCATION)));
        taskForm.setSelectList(FormNode.SQL_OUTPUT_TYPE, TaskConstants.OUTPUT_TYPES, PropertiesHandler.getOptionalProperty(PropertiesConstants.SQL_OUTPUT_TYPE));
        taskForm.getTextField(FormNode.SQL_OUTPUT_LOCATION).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.getDisplayProperty(PropertiesConstants.SQL_OUTPUT_LOCATION)));
    }
}
