package org.curtis.ui.javafx.handler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.StringUtil;

public class DbSettingsFormHandler extends FormHandler {
    public DbSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        AppProperties.addLocalPropertiesBundle();
        taskForm.getTextField("dbUsername").setText(AppProperties.getOptionalProperty("musicxml.database.username"));
        taskForm.getTextField("dbPassword").setText(AppProperties.getOptionalProperty("musicxml.database.password"));
        taskForm.getTextField("dbName").setText(AppProperties.getOptionalProperty("musicxml.database.name"));
        String server = AppProperties.getOptionalProperty("musicxml.database.server");
        if (StringUtil.isEmpty(server)) server = "localhost";
        taskForm.getTextField("dbServer").setText(server);
        ComboBox<String> dbTypeList = taskForm.getSelectList("dbType");
        ObservableList<String> dbTypes = FXCollections.observableArrayList(TaskConstants.DATABASE_TYPES);
        dbTypeList.setItems(dbTypes);
        dbTypeList.getSelectionModel().select(AppProperties.getOptionalProperty("musicxml.database.type"));
    }
}
