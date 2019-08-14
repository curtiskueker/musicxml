package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.TasksController;

public class DbTablesInitializer extends JavafxTaskInitializer  {
    public DbTablesInitializer(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeNodeMap() {
        nodeMap.put("createDatabase", tasksController.getNode("createTables"));
        nodeMap.put("generateSchema", tasksController.getNode("generateSchema"));
        nodeMap.put("schemaLocation", tasksController.getNode("schemaFileLocation"));
    }
}
