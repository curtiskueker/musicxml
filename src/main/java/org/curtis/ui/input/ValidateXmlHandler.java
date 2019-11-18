package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.bin.ValidateXml;
import org.curtis.ui.task.TaskInitializer;

public class ValidateXmlHandler extends InputHandler {
    public ValidateXmlHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        ValidateXml validateXml = new ValidateXml();
        validateXml.setXmlFileLocation(taskInitializer.getText("xmlFile"));

        return validateXml;
    }
}
