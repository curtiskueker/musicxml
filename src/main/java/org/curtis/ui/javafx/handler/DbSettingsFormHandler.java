package org.curtis.ui.javafx.handler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.curtis.database.DBConstants;
import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.util.StringUtil;

public class DbSettingsFormHandler extends FormHandler {
    public DbSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        AppProperties.addLocalPropertiesBundle();
        taskForm.getTextField(FormNode.DB_USERNAME).setText(AppProperties.getOptionalProperty("musicxml.database.username"));
        taskForm.getTextField(FormNode.DB_PASSWORD).setText(AppProperties.getOptionalProperty("musicxml.database.password"));
        taskForm.getTextField(FormNode.DB_NAME).setText(AppProperties.getOptionalProperty("musicxml.database.name"));
        String server = AppProperties.getOptionalProperty("musicxml.database.server");
        if (StringUtil.isEmpty(server)) server = "localhost";
        taskForm.getTextField(FormNode.DB_SERVER).setText(server);
        ComboBox<String> dbTypeList = taskForm.getSelectList(FormNode.DB_TYPE);
        ObservableList<String> dbTypes = FXCollections.observableArrayList(DBConstants.DATABASE_TYPES);
        dbTypeList.setItems(dbTypes);
        dbTypeList.getSelectionModel().select(AppProperties.getOptionalProperty("musicxml.database.type"));
    }
}
