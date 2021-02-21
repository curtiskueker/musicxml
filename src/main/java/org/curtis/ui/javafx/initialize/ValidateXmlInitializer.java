package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;

public class ValidateXmlInitializer extends JavafxTaskInitializer {
    public ValidateXmlInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        nodeMap.put("validateXmlSelection", taskForm.getNode(FormNode.VALIDATE_XML_SELECTION));
        nodeMap.put("compressXmlSelection", taskForm.getNode(FormNode.COMPRESS_XML_SELECTION));
        nodeMap.put("validateXmlFile", taskForm.getNode(FormNode.VALIDATE_LOCATION));
        nodeMap.put("compressXmlFile", taskForm.getNode(FormNode.COMPRESS_LOCATION));
    }
}
