package org.curtis.ui.input.conversion;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.task.TaskInitializer;
import org.curtis.util.StringUtil;

public class ToPdfInput extends ConversionInput {
    public ToPdfInput() {

    }

    @Override
    public void convertInput(MusicXmlScript musicXmlScript, TaskInitializer taskInitializer) {
        String outputDirectoryName = taskInitializer.getDirectoryLocation("outputDirectory");
        String outputFile = taskInitializer.getText("outputFile");
        if (StringUtil.isNotEmpty(outputDirectoryName)) musicXmlScript.setOutputFile(outputDirectoryName + "/" + outputFile);
        else if (StringUtil.isNotEmpty(outputFile)) musicXmlScript.setOutputFile(outputFile);
        musicXmlScript.setOpenPdf(taskInitializer.isSelected("openPdf"));
    }
}
