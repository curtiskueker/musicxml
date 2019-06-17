package org.curtis.ui.task;

import org.curtis.musicxml.bin.DatabaseExec;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
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
        testDatabase = isSelected(componentMap.get("testDatabase"));
        createDatabase = isSelected(componentMap.get("createDatabase"));
        generateSchema = isSelected(componentMap.get("generateSchema"));
        schemaLocationDirectoryName = getDirectoryLocation(componentMap.get("schemaDirectory"));
        schemaLocationFile = getText(componentMap.get("schemaFile"));
    }
}
