package org.curtis.ui.task;

import org.curtis.musicxml.bin.Ly2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
import java.util.Map;

public class Ly2PdfTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;

    public Ly2PdfTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        Ly2Pdf ly2Pdf = new Ly2Pdf();
        ly2Pdf.setInputFile(inputFileName);
        ly2Pdf.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            ly2Pdf.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() {
        inputFileName = getDirectoryLocation(componentMap.get("inputFile"));
        outputDirectoryName = getDirectoryLocation(componentMap.get("outputDirectory"));
        outputFile = getText(componentMap.get("outputFile"));
    }
}
