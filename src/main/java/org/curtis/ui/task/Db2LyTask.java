package org.curtis.ui.task;

import org.curtis.musicxml.bin.Db2Ly;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.input.Db2LyHandler;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.StringUtil;

public class Db2LyTask extends MusicXmlTask {
    private String scoreName;
    private String outputDirectoryName = "";
    private String outputFile;

    public Db2LyTask(TaskInitializer taskInitializer) {
        super(taskInitializer, new Db2LyHandler());
    }

    public void executeTask() throws TaskException {
        Db2Ly db2Ly = new Db2Ly();
        db2Ly.setScoreName(scoreName);
        if (StringUtil.isNotEmpty(outputDirectoryName)) db2Ly.setOutputFile(outputDirectoryName + "/" + outputFile);
        else if (StringUtil.isNotEmpty(outputFile)) db2Ly.setOutputFile(outputFile);

        try {
            db2Ly.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e.getMessage());
        }
    }

    public void initialize() {
        scoreName = taskInitializer.getSelection("scoreName");
        outputDirectoryName = taskInitializer.getDirectoryLocation("outputDirectory");
        outputFile = taskInitializer.getText("outputFile");
        MusicXmlUtil.INCLUDE_BREAKS = taskInitializer.isSelected("includeBreaks");
    }
}
