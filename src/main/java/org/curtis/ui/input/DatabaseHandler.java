package org.curtis.ui.input;

import org.curtis.musicxml.bin.DatabaseExec;
import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.task.TaskInitializer;
import org.curtis.util.StringUtil;

public class DatabaseHandler extends InputHandler {
    public DatabaseHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        DatabaseExec databaseExec = new DatabaseExec();
        databaseExec.setTestDatabase(taskInitializer.isSelected("testDatabase"));
        databaseExec.setCreateDatabase(taskInitializer.isSelected("createDatabase"));
        databaseExec.setGenerateSchema(taskInitializer.isSelected("generateSchema"));

        String schemaLocationDirectoryName = taskInitializer.getDirectoryLocation("schemaDirectory");
        String schemaLocation = taskInitializer.getText("schemaLocation");
        if (StringUtil.isNotEmpty(schemaLocationDirectoryName)) databaseExec.setOutputFile(schemaLocationDirectoryName + "/" + taskInitializer.getText("schemaFile"));
        else if (StringUtil.isNotEmpty(schemaLocation)) databaseExec.setOutputFile(schemaLocation);

        return databaseExec;
    }
}
