package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.TasksController;

public class SaveDbSettingsInitializer extends JavafxTaskInitializer {
    public SaveDbSettingsInitializer(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeNodeMap() {
        nodeMap.put("username", tasksController.getNode("dbUsername"));
        boolean showPassword = tasksController.checkboxOn("showPassword");
        if (showPassword) nodeMap.put("password", tasksController.getNode("dbPasswordShow"));
        else nodeMap.put("password", tasksController.getNode("dbPassword"));
        nodeMap.put("databaseName", tasksController.getNode("dbName"));
        nodeMap.put("server", tasksController.getNode("dbServer"));
        nodeMap.put("databaseType", tasksController.getNode("dbType"));
        nodeMap.put("testDatabase", tasksController.getNode("testDbConnection"));
    }
}
