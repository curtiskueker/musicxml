package org.curtis.ui.task;

import org.curtis.ui.task.exception.TaskException;

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

        //scripts/sh/musicXml2Db.sh
    }
}
