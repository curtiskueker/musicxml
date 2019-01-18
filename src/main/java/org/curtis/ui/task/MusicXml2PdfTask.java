package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class MusicXml2PdfTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;

    public MusicXml2PdfTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        MusicXml2Pdf musicXml2Pdf = new MusicXml2Pdf();
        musicXml2Pdf.INPUT_FILE = inputFileName;
        musicXml2Pdf.OUTPUT_FILE = outputDirectoryName + "/" + outputFile;

        try {
            musicXml2Pdf.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() throws TaskException {
        JFileChooser inputFileChooser = (JFileChooser) componentMap.get("inputFile");
        File inputFile = inputFileChooser.getSelectedFile();
        if (inputFile != null) inputFileName = inputFile.getAbsolutePath();
        JFileChooser outputFileChooser = (JFileChooser) componentMap.get("outputDirectory");
        File outputDirectory = outputFileChooser.getSelectedFile();
        if (outputDirectory != null) outputDirectoryName = outputDirectory.getAbsolutePath();
        JTextField outputFilefield = (JTextField) componentMap.get("outputFile");
        outputFile = outputFilefield.getText();
    }
}
