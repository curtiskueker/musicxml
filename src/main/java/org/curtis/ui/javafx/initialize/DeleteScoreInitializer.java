package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.TaskForm;

public class DeleteScoreInitializer extends JavafxTaskInitializer {
    public DeleteScoreInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("scoreName", taskForm.getNode("scoreNameFrom"));
        taskForm.setResetScoreNames();
    }
}
