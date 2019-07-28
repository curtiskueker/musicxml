package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Ly;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
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

    private void initialize() {
        inputFileName = getDirectoryLocation(componentMap.get("inputFile"));
        outputDirectoryName = getDirectoryLocation(componentMap.get("outputDirectory"));
        outputFile = getText(componentMap.get("outputFile"));
        MusicXmlUtil.INCLUDE_BREAKS = isSelected(componentMap.get("includeBreaks"));
    }
}
