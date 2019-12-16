package org.curtis.ui.javafx.handler;

import org.curtis.properties.PropertiesConstants;
import org.curtis.properties.PropertiesHandler;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;

public class OutputSettingsFormHandler extends FormHandler {
    public OutputSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        PropertiesHandler.addLocalPropertiesBundle();

        taskForm.setSelectList(FormNode.TASK_OUTPUT_TYPE, FormNode.OUTPUT_TYPES, PropertiesHandler.getOptionalProperty(PropertiesConstants.TASK_OUTPUT_TYPE));
        taskForm.getTextField(FormNode.TASK_OUTPUT_LOCATION).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.TASK_OUTPUT_LOCATION));
        taskForm.setSelectList(FormNode.SQL_OUTPUT_TYPE, FormNode.OUTPUT_TYPES, PropertiesHandler.getOptionalProperty(PropertiesConstants.SQL_OUTPUT_TYPE));
        taskForm.getTextField(FormNode.SQL_OUTPUT_LOCATION).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.SQL_OUTPUT_LOCATION));
    }
}
