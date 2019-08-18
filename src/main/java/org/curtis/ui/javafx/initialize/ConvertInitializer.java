package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.TasksController;

public class ConvertInitializer extends JavafxTaskInitializer {
    public ConvertInitializer(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeNodeMap() {
        String fromSelection = tasksController.getFromSelection();
        String toSelection = tasksController.getToSelection();

        switch (fromSelection) {
            case "MusicXml File":
                nodeMap.put("inputFile", tasksController.getNode("musicXmlFromFile"));
                break;
            case "Database Record":
                nodeMap.put("scoreName", tasksController.getNode("scoreNameFrom"));
                break;
            case "Lilypond File":
                nodeMap.put("inputFile", tasksController.getNode("lyFromFile"));
                break;
        }
        switch (toSelection) {
            case "MusicXml File":
                nodeMap.put("outputFile", tasksController.getNode("musicXmlToFile"));
                nodeMap.put("skipComments", tasksController.getNode("skipComments"));
                break;
            case "Database Record":
                nodeMap.put("scoreName", tasksController.getNode("scoreNameTo"));
                break;
            case "Lilypond File":
                nodeMap.put("outputFile", tasksController.getNode("lyToFile"));
                nodeMap.put("includeBreaks", tasksController.getNode("includePageBreaks"));
                break;
            case "PDF File":
                nodeMap.put("outputFile", tasksController.getNode("pdfToFile"));
                break;
        }
    }
}
