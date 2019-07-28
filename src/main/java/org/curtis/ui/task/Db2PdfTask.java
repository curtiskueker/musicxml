package org.curtis.ui.task;

import org.curtis.musicxml.bin.Db2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
import java.util.Map;

public class Db2PdfTask extends MusicXmlTask {
    private String scoreName;
    private String outputDirectoryName = "";
    private String outputFile;

    public Db2PdfTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        Db2Pdf db2Pdf = new Db2Pdf();
        db2Pdf.setScoreName(scoreName);
        db2Pdf.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            db2Pdf.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() {
        scoreName = getSelection(componentMap.get("scoreName"));
        outputDirectoryName = getDirectoryLocation(componentMap.get("outputDirectory"));
        outputFile = getText(componentMap.get("outputFile"));
    }
}
