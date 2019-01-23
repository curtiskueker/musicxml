package org.curtis.ui.task;

import org.curtis.musicxml.bin.DatabaseExec;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class DatabaseTask extends MusicXmlTask {
    private boolean testDatabase;
    private boolean createDatabase;
    private boolean generateSchema;
    private String schemaLocationDirectoryName = "";
    private String schemaLocationFile;

    public DatabaseTask(Map<String, Component> componentMap) {
        super(componentMap);
    }

    public void execute() throws TaskException {
        initialize();

        DatabaseExec databaseExec = new DatabaseExec();
        databaseExec.setTestDatabase(testDatabase);
        databaseExec.setCreateDatabase(createDatabase);
        databaseExec.setGenerateSchema(generateSchema);
        databaseExec.setOutputFile(schemaLocationDirectoryName + "/" + schemaLocationFile);

        try {
            databaseExec.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    private void initialize() throws TaskException {
        JCheckBox testDatabaseField = (JCheckBox)componentMap.get("testDatabase");
        testDatabase = testDatabaseField.isSelected();
        JCheckBox createDatabaseField = (JCheckBox)componentMap.get("createDatabase");
        createDatabase = createDatabaseField.isSelected();
        JCheckBox generateSchemaField = (JCheckBox)componentMap.get("generateSchema");
        generateSchema = generateSchemaField.isSelected();
        JFileChooser schemaFileChooser = (JFileChooser) componentMap.get("schemaDirectory");
        File schemaDirectory = schemaFileChooser.getSelectedFile();
        if (schemaDirectory != null) schemaLocationDirectoryName = schemaDirectory.getAbsolutePath();
        JTextField schemaFilefield = (JTextField) componentMap.get("schemaFile");
        schemaLocationFile = schemaFilefield.getText();
    }
}
