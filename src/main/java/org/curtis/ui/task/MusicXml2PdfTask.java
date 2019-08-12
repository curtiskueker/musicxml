package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

public class MusicXml2PdfTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;

    public MusicXml2PdfTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        MusicXml2Pdf musicXml2Pdf = new MusicXml2Pdf();
        musicXml2Pdf.setInputFile(inputFileName);
        musicXml2Pdf.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            musicXml2Pdf.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    public void initialize() {
        inputFileName = taskInitializer.getDirectoryLocation("inputFile");
        outputDirectoryName = taskInitializer.getDirectoryLocation("outputDirectory");
        outputFile = taskInitializer.getText("outputFile");
    }
}
