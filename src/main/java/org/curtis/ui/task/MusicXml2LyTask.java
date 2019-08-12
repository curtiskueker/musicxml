package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Ly;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.task.exception.TaskException;

public class MusicXml2LyTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;

    public MusicXml2LyTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        MusicXml2Ly musicXml2Ly = new MusicXml2Ly();
        musicXml2Ly.setInputFile(inputFileName);
        musicXml2Ly.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            musicXml2Ly.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    public void initialize() {
        inputFileName = taskInitializer.getDirectoryLocation("inputFile");
        outputDirectoryName = taskInitializer.getDirectoryLocation("outputDirectory");
        outputFile = taskInitializer.getText("outputFile");
        MusicXmlUtil.INCLUDE_BREAKS = taskInitializer.isSelected("includeBreaks");
    }
}
