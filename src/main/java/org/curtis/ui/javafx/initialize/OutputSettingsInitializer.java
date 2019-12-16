package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;

public class OutputSettingsInitializer extends JavafxTaskInitializer {
    public OutputSettingsInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("taskOutputType", taskForm.getNode(FormNode.TASK_OUTPUT_TYPE));
        nodeMap.put("sqlOutputType", taskForm.getNode(FormNode.SQL_OUTPUT_TYPE));
        nodeMap.put("taskOutputLocation", taskForm.getNode(FormNode.TASK_OUTPUT_LOCATION));
        nodeMap.put("sqlOutputLocation", taskForm.getNode(FormNode.SQL_OUTPUT_LOCATION));
    }
}
