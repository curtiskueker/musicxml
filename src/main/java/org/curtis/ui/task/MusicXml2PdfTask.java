package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXml2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.StringUtil;

public class MusicXml2PdfTask extends MusicXmlTask {
    private String inputFileName = "";
    private String outputDirectoryName = "";
    private String outputFile;
    private Boolean openPdf;

    public MusicXml2PdfTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        MusicXml2Pdf musicXml2Pdf = new MusicXml2Pdf();
        musicXml2Pdf.setInputFile(inputFileName);
        if (StringUtil.isNotEmpty(outputDirectoryName)) musicXml2Pdf.setOutputFile(outputDirectoryName + "/" + outputFile);
        else if (StringUtil.isNotEmpty(outputFile)) musicXml2Pdf.setOutputFile(outputFile);
        musicXml2Pdf.setOpenPdf(openPdf);

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
        openPdf = taskInitializer.isSelected("openPdf");
    }
}
