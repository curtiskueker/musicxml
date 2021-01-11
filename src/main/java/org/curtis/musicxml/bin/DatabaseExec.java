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
    private boolean resetDbProperties = false;
    private String errorMessage;

    public DatabaseExec() {

    }

    public void execute() throws MusicXmlException {
        if (!isCreateDatabase() && !isTestDatabase() && !isGenerateSchema() && !isResetDbProperties()) throw new MusicXmlException("No option selected.");

        try {
            if (isCreateDatabase()) {
                System.err.println("Creating database tables ...");
                errorMessage = "Unable to create database tables";
            }
            if (isTestDatabase()) {
                System.err.println("Testing database connection ...");
                errorMessage = "Database connection test failed";
            }
            if (isGenerateSchema()) {
                System.err.println("Generating database schema file ...");
                if (StringUtil.isEmpty(getOutputFile())) throw new MusicXmlException("Output file not selected.");

                errorMessage = "Database schema file not generated: " + getOutputFile();
            }

            if (isCreateDatabase() || isTestDatabase() || isGenerateSchema()) {
                MusicXmlUtil.getNewDbTransaction();
            }

            if (isCreateDatabase()) {
                DBSessionFactory.createDb();
                System.err.println("Database tables created");
            }
            if (isTestDatabase()) {
                DBSessionFactory.testDb();
                System.err.println("Successful database connection test");
            }
            if (isGenerateSchema()) {
                DBSessionFactory.generateDbSchema(getOutputFile());
                System.err.println("Database schema file output: " + getOutputFile());
            }
        } catch (DBException e) {
            throw new MusicXmlException(errorMessage);
        }
    }

    private boolean isTestDatabase() {
        return testDatabase;
    }

    public void setTestDatabase(boolean testDatabase) {
        this.testDatabase = testDatabase;
    }

    private boolean isCreateDatabase() {
        return createDatabase;
    }

    public void setCreateDatabase(boolean createDatabase) {
        this.createDatabase = createDatabase;
    }

    private boolean isGenerateSchema() {
        return generateSchema;
    }

    public void setGenerateSchema(boolean generateSchema) {
        this.generateSchema = generateSchema;
    }

    private boolean isResetDbProperties() {
        return resetDbProperties;
    }

    public void setResetDbProperties(boolean resetDbProperties) {
        this.resetDbProperties = resetDbProperties;
    }
}
