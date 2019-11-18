package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.bin.SetProperties;
import org.curtis.ui.task.TaskInitializer;

public class SetPropertiesHandler extends InputHandler {
    public SetPropertiesHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(taskInitializer.getText("username"));
        setProperties.setPassword(taskInitializer.getText("password"));
        setProperties.setDatabaseName(taskInitializer.getText("databaseName"));
        setProperties.setServer(taskInitializer.getText("server"));
        setProperties.setDatabaseType(taskInitializer.getSelection("databaseType"));
        setProperties.setLilypondLocation(taskInitializer.getDirectoryLocation("lilypondLocation"));
        setProperties.setPdfReaderLocation(taskInitializer.getDirectoryLocation("pdfReaderLocation"));

        return setProperties;
    }
}
