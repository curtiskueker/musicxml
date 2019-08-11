package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
import java.util.Map;

public class MusicXml2PdfTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;

    public MusicXml2PdfTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void executeTask() throws TaskException {
        MusicXml2Pdf musicXml2Pdf = new MusicXml2Pdf();
        musicXml2Pdf.setInputFile(inputFileName);
        musicXml2Pdf.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            musicXml2Pdf.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    public void initialize() {
        inputFileName = getDirectoryLocation(componentMap.get("inputFile"));
        outputDirectoryName = getDirectoryLocation(componentMap.get("outputDirectory"));
        outputFile = getText(componentMap.get("outputFile"));
    }
}
