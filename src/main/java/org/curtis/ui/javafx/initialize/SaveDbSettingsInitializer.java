package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.TaskForm;

public class SaveDbSettingsInitializer extends JavafxTaskInitializer {
    public SaveDbSettingsInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("username", taskForm.getNode("dbUsername"));
        boolean showPassword = taskForm.checkboxOn("showPassword");
        if (showPassword) nodeMap.put("password", taskForm.getNode("dbPasswordShow"));
        else nodeMap.put("password", taskForm.getNode("dbPassword"));
        nodeMap.put("databaseName", taskForm.getNode("dbName"));
        nodeMap.put("server", taskForm.getNode("dbServer"));
        nodeMap.put("databaseType", taskForm.getNode("dbType"));
        nodeMap.put("testDatabase", taskForm.getNode("testDbConnection"));
    }
}
