package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.TaskForm;

public class LyPdfSettingsInitializer extends JavafxTaskInitializer {
    public LyPdfSettingsInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("lilypondLocation", taskForm.getNode("lilypondLocation"));
        nodeMap.put("pdfReaderLocation", taskForm.getNode("pdfLocation"));
    }
}
