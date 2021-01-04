package org.curtis.ui.javafx.handler;

import org.curtis.database.DBConstants;
import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.util.StringUtil;

public class DbSettingsFormHandler extends FormHandler {
    public DbSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        String prefix = PropertiesConstants.PROPERTIES_PREFIX + ".";

        PropertiesHandler.addLocalProperties();
        taskForm.getTextField(FormNode.DB_USERNAME).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.getDisplayProperty(prefix + PropertiesConstants.DB_USERNAME)));
        taskForm.getTextField(FormNode.DB_PASSWORD).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.getDisplayProperty(prefix + PropertiesConstants.DB_PASSWORD)));
        taskForm.getTextField(FormNode.DB_NAME).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.getDisplayProperty(prefix + PropertiesConstants.DB_NAME)));
        String server = PropertiesHandler.getOptionalProperty(PropertiesConstants.getDisplayProperty(prefix + PropertiesConstants.DB_SERVER));
        if (StringUtil.isEmpty(server)) server = "localhost";
        taskForm.getTextField(FormNode.DB_SERVER).setText(server);
        taskForm.setSelectList(FormNode.DB_TYPE, DBConstants.DATABASE_TYPES, PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_TYPE));
    }
}
