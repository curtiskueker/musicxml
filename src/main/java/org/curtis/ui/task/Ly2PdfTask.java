package org.curtis.ui.task;

import org.curtis.musicxml.bin.Ly2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

public class Ly2PdfTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;

    public Ly2PdfTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        Ly2Pdf ly2Pdf = new Ly2Pdf();
        ly2Pdf.setInputFile(inputFileName);
        ly2Pdf.setOutputFile(outputDirectoryName + "/" + outputFile);

        try {
            ly2Pdf.execute();
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
