package org.curtis.ui.task;

import org.curtis.musicxml.bin.DatabaseExec;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.task.exception.TaskException;

public class DatabaseTask extends MusicXmlTask {
    private boolean testDatabase;
    private boolean createDatabase;
    private boolean generateSchema;
    private String schemaLocationDirectoryName = "";
    private String schemaLocationFile;

    public DatabaseTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
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

    public void initialize() {
        testDatabase = taskInitializer.isSelected("testDatabase");
        createDatabase = taskInitializer.isSelected("createDatabase");
        generateSchema = taskInitializer.isSelected("generateSchema");
        schemaLocationDirectoryName = taskInitializer.getDirectoryLocation("schemaDirectory");
        schemaLocationFile = taskInitializer.getText("schemaFile");
    }
}
