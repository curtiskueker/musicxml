package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;

public class DatabaseExec extends MusicXmlScript {
    private boolean testDatabase;
    private boolean createDatabase;
    private boolean generateSchema;

    public DatabaseExec() {

    }

    public void execute() throws MusicXmlException {
        if (testDatabase) {
            try {
                MusicXmlUtil.getNewDbTransaction();
                System.err.println("Successful database connection test");
            } catch (DBException e) {
                throw new MusicXmlException(e);
            }
        }
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
