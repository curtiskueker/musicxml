package org.curtis.ui.task;

import org.curtis.musicxml.bin.SetProperties;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class SetPropertiesTask extends MusicXmlTask {
    private String username;
    private String password;
    private String databaseName;
    private String server;
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
        setProperties.setLilypondLocation(lilypondLocation);
        setProperties.setPdfReaderLocation(pdfReaderLocation);

        try {
            setProperties.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() throws TaskException {
        JTextField usernameField = (JTextField)componentMap.get("username");
        username = usernameField.getText();
        JTextField passwordField = (JTextField)componentMap.get("password");
        password = passwordField.getText();
        JTextField databaseNameField = (JTextField)componentMap.get("databaseName");
        databaseName = databaseNameField.getText();
        JTextField serverField = (JTextField)componentMap.get("server");
        server = serverField.getText();
        JFileChooser lilypondChooser = (JFileChooser) componentMap.get("lilypondLocation");
        File lilypondFile = lilypondChooser.getSelectedFile();
        if (lilypondFile != null) lilypondLocation = lilypondFile.getAbsolutePath();
        JFileChooser pdfReaderChooser = (JFileChooser) componentMap.get("pdfReaderLocation");
        File pdfReaderFile = pdfReaderChooser.getSelectedFile();
        if (pdfReaderFile != null) pdfReaderLocation = pdfReaderFile.getAbsolutePath();
    }
}
