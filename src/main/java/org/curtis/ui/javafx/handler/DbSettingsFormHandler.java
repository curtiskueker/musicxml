package org.curtis.ui.javafx.handler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.TasksController;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.StringUtil;

public class DbSettingsFormHandler extends FormHandler {
    public DbSettingsFormHandler(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeForm() {
        AppProperties.addLocalPropertiesBundle();
        tasksController.getTextField("dbUsername").setText(AppProperties.getOptionalProperty("musicxml.database.username"));
        tasksController.getTextField("dbPassword").setText(AppProperties.getOptionalProperty("musicxml.database.password"));
        tasksController.getTextField("dbName").setText(AppProperties.getOptionalProperty("musicxml.database.name"));
        String server = AppProperties.getOptionalProperty("musicxml.database.server");
        if (StringUtil.isEmpty(server)) server = "localhost";
        tasksController.getTextField("dbServer").setText(server);
        ComboBox dbTypeList = (ComboBox)tasksController.getNode("dbType");
        ObservableList<String> dbTypes = FXCollections.observableArrayList(TaskConstants.DATABASE_TYPES);
        dbTypeList.setItems(dbTypes);
        dbTypeList.getSelectionModel().select(AppProperties.getOptionalProperty("musicxml.database.type"));
    }
}
