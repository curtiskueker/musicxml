package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.TaskForm;

public class ValidateXmlInitializer extends JavafxTaskInitializer {
    public ValidateXmlInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("xmlFile", taskForm.getNode("validateLocation"));
    }
}
