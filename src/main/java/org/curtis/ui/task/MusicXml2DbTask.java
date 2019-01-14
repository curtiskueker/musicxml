package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Db;
import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class MusicXml2DbTask extends MusicXmlTask {
    private String scoreName;
    private String inputFileName = "";

    public MusicXml2DbTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        MusicXmlScript.SCORE_NAME = scoreName;
        MusicXmlScript.INPUT_FILE = inputFileName;

        MusicXml2Db musicXml2Db = new MusicXml2Db();

        try {
            musicXml2Db.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() throws TaskException {
        JTextField scoreNameField = (JTextField) componentMap.get("scoreName");
        scoreName = scoreNameField.getText();
        JFileChooser inputFileChooser = (JFileChooser) componentMap.get("inputFile");
        File inputFile = inputFileChooser.getSelectedFile();
        if (inputFile != null) inputFileName = inputFile.getAbsolutePath();
    }
}
