package org.curtis.ui.task;

import org.curtis.musicxml.bin.DatabaseExec;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.ui.input.DatabaseHandler;
import org.curtis.ui.task.exception.TaskException;
import org.curtis.util.StringUtil;

public class DatabaseTask extends MusicXmlTask {
    private boolean testDatabase;
    private boolean createDatabase;
    private boolean generateSchema;
    private String schemaLocationDirectoryName = "";
    private String schemaLocationFile;
    private String schemaLocation;

    public DatabaseTask(TaskInitializer taskInitializer) {
        super(taskInitializer, new DatabaseHandler());
    }

    public void executeTask() throws TaskException {
        DatabaseExec databaseExec = new DatabaseExec();
        databaseExec.setTestDatabase(testDatabase);
        databaseExec.setCreateDatabase(createDatabase);
        databaseExec.setGenerateSchema(generateSchema);
        if (StringUtil.isNotEmpty(schemaLocationDirectoryName)) databaseExec.setOutputFile(schemaLocationDirectoryName + "/" + schemaLocationFile);
        else if (StringUtil.isNotEmpty(schemaLocation)) databaseExec.setOutputFile(schemaLocation);

        try {
            databaseExec.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e.getMessage());
        }
    }

    public void initialize() {
        testDatabase = taskInitializer.isSelected("testDatabase");
        createDatabase = taskInitializer.isSelected("createDatabase");
        generateSchema = taskInitializer.isSelected("generateSchema");
        schemaLocationDirectoryName = taskInitializer.getDirectoryLocation("schemaDirectory");
        schemaLocationFile = taskInitializer.getText("schemaFile");
        schemaLocation = taskInitializer.getText("schemaLocation");
    }
}
