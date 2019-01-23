package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;

public class DatabaseExec extends MusicXmlScript {
    private boolean testDatabase;
    private boolean createDatabase;
    private boolean generateSchema;

    public DatabaseExec() {

    }

    public void execute() throws MusicXmlException {

    }

    public void setTestDatabase(boolean testDatabase) {
        this.testDatabase = testDatabase;
    }

    public void setCreateDatabase(boolean createDatabase) {
        this.createDatabase = createDatabase;
    }

    public void setGenerateSchema(boolean generateSchema) {
        this.generateSchema = generateSchema;
    }
}
