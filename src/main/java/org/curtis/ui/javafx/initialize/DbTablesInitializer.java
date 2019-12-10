package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;

public class DbTablesInitializer extends JavafxTaskInitializer  {
    public DbTablesInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("createDatabase", taskForm.getNode(FormNode.CREATE_DB_TABLES));
        nodeMap.put("generateSchema", taskForm.getNode(FormNode.GENERATE_SCHEMA));
        nodeMap.put("schemaLocation", taskForm.getNode(FormNode.SCHEMA_FILE_LOCATION));
    }
}
