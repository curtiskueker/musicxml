package org.curtis.ui.javafx.handler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.curtis.properties.PropertiesHandler;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;

public class OutputSettingsFormHandler extends FormHandler {
    public OutputSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        PropertiesHandler.addLocalPropertiesBundle();

        ObservableList<String> outputTypes = FXCollections.observableArrayList(FormNode.OUTPUT_TYPES);
        ComboBox<String> taskOutputList = taskForm.getSelectList(FormNode.TASK_OUTPUT_TYPE);
        taskOutputList.setItems(outputTypes);
        ComboBox<String> sqlOutputList = taskForm.getSelectList(FormNode.SQL_OUTPUT_TYPE);
        sqlOutputList.setItems(outputTypes);
    }
}
