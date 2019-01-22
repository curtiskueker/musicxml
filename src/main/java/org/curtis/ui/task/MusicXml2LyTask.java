package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Ly;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class MusicXml2LyTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;

    public MusicXml2LyTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        MusicXml2Ly musicXml2Ly = new MusicXml2Ly();
        musicXml2Ly.setInputFile(inputFileName);
        musicXml2Ly.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            musicXml2Ly.execute();
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
