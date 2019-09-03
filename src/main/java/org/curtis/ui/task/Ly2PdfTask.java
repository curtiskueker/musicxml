package org.curtis.ui.task;

import org.curtis.musicxml.bin.Ly2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.StringUtil;

public class Ly2PdfTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;
    private Boolean openPdf;

    public Ly2PdfTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        Ly2Pdf ly2Pdf = new Ly2Pdf();
        ly2Pdf.setInputFile(inputFileName);
        if (StringUtil.isNotEmpty(outputDirectoryName)) ly2Pdf.setOutputFile(outputDirectoryName + "/" + outputFile);
        else if (StringUtil.isNotEmpty(outputFile)) ly2Pdf.setOutputFile(outputFile);
        ly2Pdf.setOpenPdf(openPdf);

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
        openPdf = taskInitializer.isSelected("openPdf");
    }
}
