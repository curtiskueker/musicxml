package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.TasksController;

public class LyPdfSettingsInitializer extends JavafxTaskInitializer {
    public LyPdfSettingsInitializer(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeNodeMap() {
        nodeMap.put("lilypondLocation", tasksController.getNode("lilypondLocation"));
        nodeMap.put("pdfReaderLocation", tasksController.getNode("pdfLocation"));
    }
}
