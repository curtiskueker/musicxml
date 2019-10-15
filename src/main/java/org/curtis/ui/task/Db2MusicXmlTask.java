package org.curtis.ui.task;

import org.curtis.musicxml.bin.Db2MusicXml;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.StringUtil;

public class Db2MusicXmlTask extends MusicXmlTask {
    private String scoreName;
    private String outputDirectoryName = "";
    private String outputFile;
    private boolean skipComments;

    public Db2MusicXmlTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        Db2MusicXml db2MusicXml = new Db2MusicXml();
        db2MusicXml.setScoreName(scoreName);
        if (StringUtil.isNotEmpty(outputDirectoryName)) db2MusicXml.setOutputFile(outputDirectoryName + "/" + outputFile);
        else if (StringUtil.isNotEmpty(outputFile)) db2MusicXml.setOutputFile(outputFile);
        db2MusicXml.setSkipComments(skipComments);

        try {
            db2MusicXml.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e.getMessage());
        }
    }

    public void initialize() {
        scoreName = taskInitializer.getSelection("scoreName");
        outputDirectoryName = taskInitializer.getDirectoryLocation("outputDirectory");
        outputFile = taskInitializer.getText("outputFile");
        skipComments = taskInitializer.isSelected("skipComments");
    }
}
