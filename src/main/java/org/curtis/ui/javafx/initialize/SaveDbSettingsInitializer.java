package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;

public class SaveDbSettingsInitializer extends JavafxTaskInitializer {
    public SaveDbSettingsInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("username", taskForm.getNode(FormNode.DB_USERNAME));
        boolean showPassword = taskForm.checkboxOn(FormNode.SHOW_PASSWORD);
        if (showPassword) nodeMap.put("password", taskForm.getNode(FormNode.DB_PASSWORD_SHOW));
        else nodeMap.put("password", taskForm.getNode(FormNode.DB_PASSWORD));
        nodeMap.put("databaseName", taskForm.getNode(FormNode.DB_NAME));
        nodeMap.put("server", taskForm.getNode(FormNode.DB_SERVER));
        nodeMap.put("databaseType", taskForm.getNode(FormNode.DB_TYPE));
        nodeMap.put("testDatabase", taskForm.getNode(FormNode.DB_TEST_CONNECTION));
    }
}
