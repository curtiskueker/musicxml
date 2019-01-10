package org.curtis.ui.task;

import org.curtis.exception.FileException;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DatabasePropertiesTask extends MusicXmlTask {
    private String username;
    private String password;
    private String databaseName;
    private String server;
    private boolean createUser;
    private boolean createDatabase;

    public DatabasePropertiesTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        String homeDirectory = System.getProperty("user.home");
        if (StringUtil.isEmpty(homeDirectory)) throw new TaskException("User home directory not found");

        String propertiesFilename = homeDirectory + "/.musicxml/musicxml.properties";

        // write properties to file
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getPropertyString("username", username));
        stringBuilder.append(getPropertyString("password", password));
        stringBuilder.append(getPropertyString("name", databaseName));
        stringBuilder.append(getPropertyString("server", server));

        try {
            FileUtil.stringToFile(stringBuilder.toString(), propertiesFilename);
        } catch (FileException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() {
        JTextField usernameField = (JTextField)componentMap.get("username");
        username = usernameField.getText();
        JTextField passwordField = (JTextField)componentMap.get("password");
        password = passwordField.getText();
        JTextField databaseNameField = (JTextField)componentMap.get("databaseName");
        databaseName = databaseNameField.getText();
        JTextField serverField = (JTextField)componentMap.get("server");
        server = serverField.getText();
        JCheckBox createUserField = (JCheckBox)componentMap.get("createUser");
        createUser = createUserField.isSelected();
        JCheckBox createDatabaseField = (JCheckBox)componentMap.get("createDatabase");
        createDatabase = createDatabaseField.isSelected();
    }

    private String getPropertyString(String propertyName, String propertyValue) {
        if (StringUtil.isEmpty(propertyValue)) return "";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("database.musicxml.");
        stringBuilder.append(propertyName);
        stringBuilder.append("=");
        stringBuilder.append(propertyValue);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
