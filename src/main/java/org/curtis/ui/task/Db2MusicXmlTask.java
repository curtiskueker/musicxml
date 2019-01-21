package org.curtis.ui.task;

import org.curtis.musicxml.bin.Db2MusicXml;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class Db2MusicXmlTask extends MusicXmlTask {
    private String scoreName;
    private String outputDirectoryName = "";
    private String outputFile;
    private boolean skipComments;

    public Db2MusicXmlTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        Db2MusicXml db2MusicXml = new Db2MusicXml();
        db2MusicXml.SCORE_NAME = scoreName;
        db2MusicXml.OUTPUT_FILE = outputDirectoryName + "/" + outputFile;
        db2MusicXml.SKIP_COMMENTS = skipComments;

        try {
            db2MusicXml.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() throws TaskException {
        JComboBox scoreNameSelection = (JComboBox) componentMap.get("scoreName");
        scoreName = (String)scoreNameSelection.getSelectedItem();
        JFileChooser outputFileChooser = (JFileChooser) componentMap.get("outputDirectory");
        File outputDirectory = outputFileChooser.getSelectedFile();
        if (outputDirectory != null) outputDirectoryName = outputDirectory.getAbsolutePath();
        JTextField outputFilefield = (JTextField) componentMap.get("outputFile");
        outputFile = outputFilefield.getText();
        JCheckBox skipCommentsField = (JCheckBox)componentMap.get("skipComments");
        skipComments = skipCommentsField.isSelected();
    }
}
