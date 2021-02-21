package org.curtis.ui.input;

import org.curtis.musicxml.bin.CompressXml;
import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.bin.ValidateXml;
import org.curtis.ui.task.TaskInitializer;

public class ValidateXmlHandler extends InputHandler {
    public ValidateXmlHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        MusicXmlScript musicXmlScript;
        if (taskInitializer.isSelected("validateXmlSelection")) musicXmlScript = new ValidateXml();
        else if (taskInitializer.isSelected("compressXmlSelection")) musicXmlScript = new CompressXml();
        else return null;

        musicXmlScript.setInputFile(taskInitializer.getText("validateXmlFile"));
        musicXmlScript.setOutputFile(taskInitializer.getText("compressXmlFile"));

        return musicXmlScript;
    }
}
