package org.curtis.ui.input.conversion;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.task.TaskInitializer;
import org.curtis.util.StringUtil;

public class ToLyInput extends ConversionInput {
    public ToLyInput() {

    }

    @Override
    public void convertInput(MusicXmlScript musicXmlScript, TaskInitializer taskInitializer) {
        String outputDirectoryName = taskInitializer.getDirectoryLocation("outputDirectory");
        String outputFile = taskInitializer.getText("outputFile");
        if (StringUtil.isNotEmpty(outputDirectoryName)) musicXmlScript.setOutputFile(outputDirectoryName + "/" + outputFile);
        else if (StringUtil.isNotEmpty(outputFile)) musicXmlScript.setOutputFile(outputFile);
        MusicXmlUtil.INCLUDE_BREAKS = taskInitializer.isSelected("includeBreaks");
    }
}
