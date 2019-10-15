package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Db;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

public class MusicXml2DbTask extends MusicXmlTask {
    private String scoreName;
    private String inputFileName = "";

    public MusicXml2DbTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        MusicXml2Db musicXml2Db = new MusicXml2Db();
        musicXml2Db.setScoreName(scoreName);
        musicXml2Db.setInputFile(inputFileName);

        try {
            musicXml2Db.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e.getMessage());
        }
    }

    public void initialize() {
        scoreName = taskInitializer.getText("scoreName");
        inputFileName = taskInitializer.getDirectoryLocation("inputFile");
    }
}
