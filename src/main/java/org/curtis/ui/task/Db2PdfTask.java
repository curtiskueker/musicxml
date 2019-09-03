package org.curtis.ui.task;

import org.curtis.musicxml.bin.Db2Pdf;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.StringUtil;

public class Db2PdfTask extends MusicXmlTask {
    private String scoreName;
    private String outputDirectoryName = "";
    private String outputFile;
    private Boolean openPdf;

    public Db2PdfTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        Db2Pdf db2Pdf = new Db2Pdf();
        db2Pdf.setScoreName(scoreName);
        if (StringUtil.isNotEmpty(outputDirectoryName)) db2Pdf.setOutputFile(outputDirectoryName + "/" + outputFile);
        else if (StringUtil.isNotEmpty(outputFile)) db2Pdf.setOutputFile(outputFile);
        db2Pdf.setOpenPdf(openPdf);

        try {
            db2Pdf.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    public void initialize() {
        scoreName = taskInitializer.getSelection("scoreName");
        outputDirectoryName = taskInitializer.getDirectoryLocation("outputDirectory");
        outputFile = taskInitializer.getText("outputFile");
        openPdf = taskInitializer.isSelected("openPdf");
    }
}
