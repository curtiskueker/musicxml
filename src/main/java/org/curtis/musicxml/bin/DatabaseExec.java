package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.StringUtil;

public class DatabaseExec extends MusicXmlScript {
    private boolean testDatabase;
    private boolean createDatabase;
    private boolean generateSchema;

    public DatabaseExec() {

    }

    public void execute() throws MusicXmlException {
        try {
            if (isCreateDatabase()) {
                DBSessionFactory.createDb();
                System.err.println("Database created");
            }
            if (isTestDatabase()) {
                MusicXmlUtil.getNewDbTransaction();
                System.err.println("Successful database connection test");
            }
            if (isGenerateSchema()) {
                MusicXmlUtil.getNewDbTransaction();
                DBSessionFactory.generateDbSchema(getOutputFile());
                System.err.println("Database schema file output: " + getOutputFile());
            }
        } catch (DBException e) {
            throw new MusicXmlException(e);
        }
    }

    public boolean isTestDatabase() {
        return testDatabase;
    }

    public void setTestDatabase(boolean testDatabase) {
        this.testDatabase = testDatabase;
    }

    public boolean isCreateDatabase() {
        return createDatabase;
    }

    public void setCreateDatabase(boolean createDatabase) {
        this.createDatabase = createDatabase;
    }

    public boolean isGenerateSchema() {
        return generateSchema;
    }

    public void setGenerateSchema(boolean generateSchema) {
        this.generateSchema = generateSchema;
    }

    public static void main(String[] args) {
        DatabaseExec databaseExec = new DatabaseExec();
        for (String arg : args) {
            if (arg.startsWith("SCHEMA_FILE=")) {
                String schemaFile = arg.replace("SCHEMA_FILE=", "");
                databaseExec.setGenerateSchema(StringUtil.isNotEmpty(schemaFile));
                databaseExec.setOutputFile(schemaFile);
            } else if (arg.equals("CREATE_DATABASE")) {
                databaseExec.setCreateDatabase(true);
            } else if (arg.equals("TEST_DATABASE")) {
                databaseExec.setTestDatabase(true);
            }
        }

        try {
            databaseExec.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
