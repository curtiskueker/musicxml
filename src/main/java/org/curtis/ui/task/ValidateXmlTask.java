package org.curtis.ui.task;

import org.curtis.musicxml.bin.ValidateXml;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.input.ValidateXmlHandler;
import org.curtis.ui.task.exception.TaskException;

public class ValidateXmlTask extends MusicXmlTask {
    private String xmlFileLocation;

    public ValidateXmlTask(TaskInitializer taskInitializer) {
        super(taskInitializer, new ValidateXmlHandler());
    }

    public void executeTask() throws TaskException {
        ValidateXml validateXml = new ValidateXml();
        validateXml.setXmlFileLocation(xmlFileLocation);

        try {
            validateXml.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e.getMessage());
        }
    }

    public void initialize() {
        xmlFileLocation = taskInitializer.getText("xmlFile");
    }
}
