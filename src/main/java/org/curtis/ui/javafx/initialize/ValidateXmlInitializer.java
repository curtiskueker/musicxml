package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.TasksController;

public class ValidateXmlInitializer extends JavafxTaskInitializer  {
    public ValidateXmlInitializer(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeNodeMap() {
        nodeMap.put("xmlFile", tasksController.getNode("validateLocation"));
    }
}
