package org.curtis.ui.task;

import org.curtis.musicxml.bin.DatabaseExec;
import org.curtis.musicxml.bin.SetProperties;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.properties.AppProperties;
import org.curtis.ui.task.exception.TaskException;

public class SetDbPropertiesTask extends MusicXmlTask {
    private String username;
    private String password;
    private String databaseName;
    private String server;
    private String databaseType;
    private boolean testDatabase;

    public SetDbPropertiesTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(username);
        setProperties.setPassword(password);
        setProperties.setDatabaseName(databaseName);
        setProperties.setServer(server);
        setProperties.setDatabaseType(databaseType);
        setProperties.setLilypondLocation(AppProperties.getOptionalProperty("location.lilypond"));
        setProperties.setPdfReaderLocation(AppProperties.getOptionalProperty("location.pdfreader"));

        DatabaseExec databaseExec = new DatabaseExec();
        databaseExec.setTestDatabase(testDatabase);

        try {
            System.err.println("SET PROPERTIES START");
            setProperties.execute();
            System.err.println("SET PROPERTIES END");
            System.err.println("TEST DATABASE START");
            if (testDatabase) databaseExec.execute();
            System.err.println("TEST DATABASE END");
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    public void initialize() {
        username = taskInitializer.getText("username");
        password = taskInitializer.getText("password");
        databaseName = taskInitializer.getText("databaseName");
        server = taskInitializer.getText("server");
        databaseType = taskInitializer.getSelection("databaseType");
        testDatabase = taskInitializer.isSelected("testDatabase");
    }
}
