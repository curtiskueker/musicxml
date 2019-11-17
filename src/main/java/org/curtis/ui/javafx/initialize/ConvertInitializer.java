package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.ui.task.TaskConstants;

public class ConvertInitializer extends JavafxTaskInitializer {
    public ConvertInitializer(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeNodeMap() {
        String fromSelection = taskForm.getFromSelection();
        String toSelection = taskForm.getToSelection();

        switch (fromSelection) {
            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                nodeMap.put("inputFile", taskForm.getNode("musicXmlFromFile"));
                break;
            case TaskConstants.CONVERSION_TYPE_DATABASE:
                nodeMap.put("scoreName", taskForm.getNode("scoreNameFrom"));
                break;
            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                nodeMap.put("inputFile", taskForm.getNode("lyFromFile"));
                break;
        }
        switch (toSelection) {
            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                nodeMap.put("outputFile", taskForm.getNode("musicXmlToFile"));
                nodeMap.put("skipComments", taskForm.getNode("skipComments"));
                break;
            case TaskConstants.CONVERSION_TYPE_DATABASE:
                nodeMap.put("scoreName", taskForm.getNode("scoreNameTo"));
                break;
            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                nodeMap.put("outputFile", taskForm.getNode("lyToFile"));
                nodeMap.put("includeBreaks", taskForm.getNode("includePageBreaks"));
                break;
            case TaskConstants.CONVERSION_TYPE_PDF:
                nodeMap.put("outputFile", taskForm.getNode("pdfToFile"));
                nodeMap.put("openPdf", taskForm.getNode("openPdf"));
                break;
        }
    }
}
