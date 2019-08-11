package org.curtis.ui.task;

import org.curtis.musicxml.bin.Db2Ly;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
import java.util.Map;

public class Db2LyTask extends MusicXmlTask {
    private String scoreName;
    private String outputDirectoryName = "";
    private String outputFile;

    public Db2LyTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void executeTask() throws TaskException {
        Db2Ly db2Ly = new Db2Ly();
        db2Ly.setScoreName(scoreName);
        db2Ly.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            db2Ly.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    public void initialize() {
        scoreName = getSelection(componentMap.get("scoreName"));
        outputDirectoryName = getDirectoryLocation(componentMap.get("outputDirectory"));
        outputFile = getText(componentMap.get("outputFile"));
        MusicXmlUtil.INCLUDE_BREAKS = isSelected(componentMap.get("includeBreaks"));
    }
}
