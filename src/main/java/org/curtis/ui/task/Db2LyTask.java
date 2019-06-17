package org.curtis.ui.task;

import org.curtis.musicxml.bin.Db2Ly;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class Db2LyTask extends MusicXmlTask {
    private String scoreName;
    private String outputDirectoryName = "";
    private String outputFile;

    public Db2LyTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        Db2Ly db2Ly = new Db2Ly();
        db2Ly.setScoreName(scoreName);
        db2Ly.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            db2Ly.execute();
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
        JCheckBox includeBreaksField = (JCheckBox)componentMap.get("includeBreaks");
        MusicXmlUtil.INCLUDE_BREAKS = includeBreaksField.isSelected();
    }
}
