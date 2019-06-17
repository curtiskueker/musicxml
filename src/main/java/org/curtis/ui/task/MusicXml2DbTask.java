package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Db;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
import java.util.Map;

public class MusicXml2DbTask extends MusicXmlTask {
    private String scoreName;
    private String inputFileName = "";

    public MusicXml2DbTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        MusicXml2Db musicXml2Db = new MusicXml2Db();
        musicXml2Db.setScoreName(scoreName);
        musicXml2Db.setInputFile(inputFileName);

        try {
            musicXml2Db.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() throws TaskException {
        scoreName = getText(componentMap.get("scoreName"));
        inputFileName = getDirectoryLocation(componentMap.get("inputFile"));
    }
}
