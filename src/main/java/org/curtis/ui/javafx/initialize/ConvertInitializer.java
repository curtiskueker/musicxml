package org.curtis.ui.javafx.initialize;

import org.curtis.ui.javafx.form.FormNode;
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
                nodeMap.put("inputFile", taskForm.getNode(FormNode.MUSICXML_FROM_FILE));
                break;
            case TaskConstants.CONVERSION_TYPE_DATABASE:
                nodeMap.put("scoreName", taskForm.getNode(FormNode.SCORE_NAME_FROM));
                break;
            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                nodeMap.put("inputFile", taskForm.getNode(FormNode.LY_FROM_FILE));
                break;
        }
        switch (toSelection) {
            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                nodeMap.put("outputFile", taskForm.getNode(FormNode.MUSICXML_TO_FILE));
                nodeMap.put("skipComments", taskForm.getNode(FormNode.SKIP_COMMENTS));
                nodeMap.put("zippedFile", taskForm.getNode(FormNode.MUSICXML_TO_ZIPPED_FILE));
                break;
            case TaskConstants.CONVERSION_TYPE_DATABASE:
                nodeMap.put("scoreName", taskForm.getNode(FormNode.SCORE_NAME_TO));
                taskForm.setResetScoreNames();
                break;
            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                nodeMap.put("outputFile", taskForm.getNode(FormNode.LY_TO_FILE));
                nodeMap.put("includeBreaks", taskForm.getNode(FormNode.INCLUDE_PAGE_BREAKS));
                break;
            case TaskConstants.CONVERSION_TYPE_PDF:
                nodeMap.put("outputFile", taskForm.getNode(FormNode.PDF_TO_FILE));
                nodeMap.put("openPdf", taskForm.getNode(FormNode.OPEN_PDF));
                break;
        }
    }
}
