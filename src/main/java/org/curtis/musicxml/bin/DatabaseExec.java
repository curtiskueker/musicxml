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
    private String errorMessage;

    public DatabaseExec() {

    }

    public void execute() throws MusicXmlException {
        try {
            if (isCreateDatabase()) {
                errorMessage = "Unable to create database tables";
                System.err.println("Creating database tables ...");
                DBSessionFactory.createDb();
                System.err.println("Database tables created");
            }
            if (isTestDatabase()) {
                errorMessage = "Database connection test failed";
                MusicXmlUtil.getNewDbTransaction();
                System.err.println("Successful database connection test");
            }
            if (isGenerateSchema()) {
                errorMessage = "Database schema file not generated: " + getOutputFile();
                MusicXmlUtil.getNewDbTransaction();
                DBSessionFactory.generateDbSchema(getOutputFile());
                System.err.println("Database schema file output: " + getOutputFile());
            }
        } catch (DBException e) {
            // TODO: remove print stack trace
            e.printStackTrace();
            throw new MusicXmlException(errorMessage);
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
