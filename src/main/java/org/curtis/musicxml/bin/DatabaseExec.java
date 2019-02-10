package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;

public class DatabaseExec extends MusicXmlScript {
    private boolean testDatabase;

    public DatabaseExec() {

    }

    public void execute() throws MusicXmlException {
        try {
            if (isCreateDatabase()) {
                DBSessionFactory.createDb();
            }
            if (testDatabase) {
                MusicXmlUtil.getNewDbTransaction();
                System.err.println("Successful database connection test");
            }
            if (isGenerateSchema()) {
                DBSessionFactory.generateDbSchema(getOutputFile());
            }
        } catch (DBException e) {
            throw new MusicXmlException(e);
        }
    }

    public void setTestDatabase(boolean testDatabase) {
        this.testDatabase = testDatabase;
    }
}
