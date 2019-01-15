package org.curtis.ui.task;

import org.curtis.exception.FileException;
import org.curtis.musicxml.bin.MusicXml2Db;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.AppProperties;
import org.curtis.properties.PropertyFileNotFoundException;
import org.curtis.ui.MusicXmlTasks;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

import static org.curtis.ui.MusicXmlTasks.PROPERTIES_BUNDLE;
import static org.curtis.ui.MusicXmlTasks.PROPERTIES_DIRECTORY;

public class SetPropertiesTask extends MusicXmlTask {
    private String username;
    private String password;
    private String databaseName;
    private String server;
    private boolean createDatabase;
    private String lilypondLocation = "";
    private String pdfReaderLocation = "";

    public SetPropertiesTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        // write properties to file
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getPropertyString("musicxml.database.username", username));
        stringBuilder.append(getPropertyString("musicxml.database.password", password));
        stringBuilder.append(getPropertyString("musicxml.database.name", databaseName));
        stringBuilder.append(getPropertyString("musicxml.database.server", server));
        stringBuilder.append(getPropertyString("location.lilypond", lilypondLocation));
        stringBuilder.append(getPropertyString("location.pdfreader", pdfReaderLocation));

        try {
            FileUtil.stringToFile(stringBuilder.toString(), MusicXmlTasks.PROPERTIES_FILENAME + ".properties");
        } catch (FileException e) {
            throw new TaskException(e);
        }
        try {
            AppProperties.addPropertiesBundle(PROPERTIES_DIRECTORY, PROPERTIES_BUNDLE);
        } catch (PropertyFileNotFoundException e) {
            //
        }

        if (createDatabase) {
            MusicXml2Db musicXml2Db = new MusicXml2Db();
            MusicXmlUtil.CREATE_DB_SCHEMA = true;
            try {
                musicXml2Db.execute();
            } catch (MusicXmlException e) {
                throw new TaskException(e);
            }
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
        JCheckBox createDatabaseField = (JCheckBox)componentMap.get("createDatabase");
        createDatabase = createDatabaseField.isSelected();
        JFileChooser lilypondChooser = (JFileChooser) componentMap.get("lilypondLocation");
        File lilypondFile = lilypondChooser.getSelectedFile();
        if (lilypondFile != null) lilypondLocation = lilypondFile.getAbsolutePath();
        JFileChooser pdfReaderChooser = (JFileChooser) componentMap.get("pdfReaderLocation");
        File pdfReaderFile = pdfReaderChooser.getSelectedFile();
        if (pdfReaderFile != null) pdfReaderLocation = pdfReaderFile.getAbsolutePath();
    }

    private String getPropertyString(String propertyName, String propertyValue) {
        if (StringUtil.isEmpty(propertyValue)) return "";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(propertyName);
        stringBuilder.append("=");
        stringBuilder.append(propertyValue);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
