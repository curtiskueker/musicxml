package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.TaskForm;

public class DbTablesInitializer extends JavafxTaskInitializer  {
    public DbTablesInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("createDatabase", taskForm.getNode("createTables"));
        nodeMap.put("generateSchema", taskForm.getNode("generateSchema"));
        nodeMap.put("schemaLocation", taskForm.getNode("schemaFileLocation"));
    }
}
