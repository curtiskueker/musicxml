package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.TasksController;
import org.curtis.ui.task.TaskConstants;

public class ConvertInitializer extends JavafxTaskInitializer {
    public ConvertInitializer(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeNodeMap() {
        String fromSelection = tasksController.getFromSelection();
        String toSelection = tasksController.getToSelection();

        switch (fromSelection) {
            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                nodeMap.put("inputFile", tasksController.getNode("musicXmlFromFile"));
                break;
            case TaskConstants.CONVERSION_TYPE_DATABASE:
                nodeMap.put("scoreName", tasksController.getNode("scoreNameFrom"));
                break;
            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                nodeMap.put("inputFile", tasksController.getNode("lyFromFile"));
                break;
        }
        switch (toSelection) {
            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                nodeMap.put("outputFile", tasksController.getNode("musicXmlToFile"));
                nodeMap.put("skipComments", tasksController.getNode("skipComments"));
                break;
            case TaskConstants.CONVERSION_TYPE_DATABASE:
                nodeMap.put("scoreName", tasksController.getNode("scoreNameTo"));
                break;
            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                nodeMap.put("outputFile", tasksController.getNode("lyToFile"));
                nodeMap.put("includeBreaks", tasksController.getNode("includePageBreaks"));
                break;
            case TaskConstants.CONVERSION_TYPE_PDF:
                nodeMap.put("outputFile", tasksController.getNode("pdfToFile"));
                nodeMap.put("openPdf", tasksController.getNode("openPdf"));
                break;
        }
    }
}
