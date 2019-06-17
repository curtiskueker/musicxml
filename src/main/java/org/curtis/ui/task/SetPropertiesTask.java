package org.curtis.ui.task;

import org.curtis.musicxml.bin.SetProperties;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
import java.util.Map;

public class SetPropertiesTask extends MusicXmlTask {
    private String username;
    private String password;
    private String databaseName;
    private String server;
    private String databaseType;
    private String lilypondLocation;
    private String pdfReaderLocation;

    public SetPropertiesTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

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

    private void initialize() throws TaskException {
        username = getText(componentMap.get("username"));
        password = getText(componentMap.get("password"));
        databaseName = getText(componentMap.get("databaseName"));
        server = getText(componentMap.get("server"));
        databaseType = getSelection(componentMap.get("databaseType"));
        lilypondLocation = getDirectoryLocation(componentMap.get("lilypondLocation"));
        pdfReaderLocation = getDirectoryLocation(componentMap.get("pdfReaderLocation"));
    }
}
