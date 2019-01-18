package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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

        MusicXmlScript.SCORE_NAME = scoreName;
        MusicXmlScript.OUTPUT_FILE = outputDirectoryName + "/" + outputFile;
    }

    private void initialize() throws TaskException {
        JComboBox scoreNameSelection = (JComboBox) componentMap.get("scoreName");
        scoreName = (String)scoreNameSelection.getSelectedItem();
        JFileChooser outputFileChooser = (JFileChooser) componentMap.get("outputDirectory");
        File outputDirectory = outputFileChooser.getSelectedFile();
        if (outputDirectory != null) outputDirectoryName = outputDirectory.getAbsolutePath();
        JTextField outputFilefield = (JTextField) componentMap.get("outputFile");
        outputFile = outputFilefield.getText();
    }
}
