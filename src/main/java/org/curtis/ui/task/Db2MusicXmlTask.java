package org.curtis.ui.task;

import org.curtis.musicxml.bin.Db2MusicXml;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
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
        db2MusicXml.setScoreName(scoreName);
        db2MusicXml.setOutputFile(outputDirectoryName + "/" + outputFile);
        db2MusicXml.setSkipComments(skipComments);

        try {
            db2MusicXml.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() throws TaskException {
        scoreName = getSelection(componentMap.get("scoreName"));
        outputDirectoryName = getDirectoryLocation(componentMap.get("outputDirectory"));
        outputFile = getText(componentMap.get("outputFile"));
        skipComments = isSelected(componentMap.get("skipComments"));
    }
}
