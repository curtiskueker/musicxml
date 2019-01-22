package org.curtis.ui.task;

import org.curtis.musicxml.bin.Ly2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
