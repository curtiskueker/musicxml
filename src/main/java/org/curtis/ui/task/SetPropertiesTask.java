package org.curtis.ui.task;

import org.curtis.musicxml.bin.SetProperties;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

public class SetPropertiesTask extends MusicXmlTask {
    private String username;
    private String password;
    private String databaseName;
    private String server;
    private String databaseType;
    private String lilypondLocation;
    private String pdfReaderLocation;

    public SetPropertiesTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(username);
        setProperties.setPassword(password);
        setProperties.setDatabaseName(databaseName);
        setProperties.setServer(server);
        setProperties.setDatabaseType(databaseType);
        setProperties.setLilypondLocation(lilypondLocation);
        setProperties.setPdfReaderLocation(pdfReaderLocation);

        try {
            setProperties.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    public void initialize() {
        username = taskInitializer.getText("username");
        password = taskInitializer.getText("password");
        databaseName = taskInitializer.getText("databaseName");
        server = taskInitializer.getText("server");
        databaseType = taskInitializer.getSelection("databaseType");
        lilypondLocation = taskInitializer.getDirectoryLocation("lilypondLocation");
        pdfReaderLocation = taskInitializer.getDirectoryLocation("pdfReaderLocation");
    }
}
