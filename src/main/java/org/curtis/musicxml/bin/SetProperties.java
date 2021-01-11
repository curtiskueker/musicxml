package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.exception.FileException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.EncryptionHandler;
import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;

public class SetProperties extends MusicXmlScript {
    private String username;
    private String password;
    private String databaseName;
    private String server;
    private String databaseType;
    private String lilypondLocation;
    private String pdfReaderLocation;
    private String taskOutputType;
    private String taskOutputLocation;
    private String sqlOutputType;
    private String sqlOutputLocation;

    public SetProperties() {

    }

    public void execute() throws MusicXmlException {
        if (StringUtil.isNotEmpty(taskOutputType) && taskOutputType.equals(TaskConstants.OUTPUT_TO_FILE) && StringUtil.isEmpty(taskOutputLocation))
            throw new MusicXmlException("Task output filename is required");
        if (StringUtil.isNotEmpty(sqlOutputType) && sqlOutputType.equals(TaskConstants.OUTPUT_TO_FILE) && StringUtil.isEmpty(sqlOutputLocation))
            throw new MusicXmlException("SQL output filename is required");

        // write properties to file
        String prefix = PropertiesConstants.PROPERTIES_PREFIX + ".";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_USERNAME, username));
        stringBuilder.append(getEncodedPropertyString(prefix + PropertiesConstants.DB_PASSWORD, password));
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_NAME, databaseName));
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_SERVER, server));
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_TYPE, databaseType));
        stringBuilder.append(getPropertyString(PropertiesConstants.LILYPOND_LOCATION, lilypondLocation));
        stringBuilder.append(getPropertyString(PropertiesConstants.PDF_LOCATION, pdfReaderLocation));
        stringBuilder.append(getPropertyString(PropertiesConstants.TASK_OUTPUT_TYPE, taskOutputType));
        stringBuilder.append(getPropertyString(PropertiesConstants.TASK_OUTPUT_LOCATION, taskOutputLocation));
        stringBuilder.append(getPropertyString(PropertiesConstants.SQL_OUTPUT_TYPE, sqlOutputType));
        stringBuilder.append(getPropertyString(PropertiesConstants.SQL_OUTPUT_LOCATION, sqlOutputLocation));

        try {
            FileUtil.stringToFile(stringBuilder.toString(), PropertiesHandler.LOCAL_PROPERTIES_FILENAME);
        } catch (FileException e) {
            throw new MusicXmlException(e.getMessage());
        }

        try {
            MusicXmlUtil.clearDb();
            PropertiesHandler.addLocalProperties();
        } catch (DBException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    private String getEncodedPropertyString(String propertyName, String propertyValue) {
        if (StringUtil.isEmpty(propertyValue)) return "";

        if (!PropertiesHandler.isEncryptedProperty(propertyValue)) propertyValue = EncryptionHandler.getInstance().getEncryptedValue(propertyValue);

        return getPropertyString(propertyName, propertyValue);
    }

    private String getPropertyString(String propertyName, String propertyValue) {
        if (StringUtil.isEmpty(propertyValue)) return "";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(propertyName);
        stringBuilder.append("=");
        stringBuilder.append(propertyValue.replace("\\", "/"));
        stringBuilder.append("\n");

        String displayPropertyName = PropertiesConstants.getDisplayProperty(propertyName);
        if (StringUtil.isNotEmpty(displayPropertyName)) {
            stringBuilder.append(displayPropertyName);
            stringBuilder.append("=");
            stringBuilder.append(propertyValue.replace("\\", "\\\\"));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public void setLilypondLocation(String lilypondLocation) {
        this.lilypondLocation = lilypondLocation;
    }

    public void setPdfReaderLocation(String pdfReaderLocation) {
        this.pdfReaderLocation = pdfReaderLocation;
    }

    public void setTaskOutputType(String taskOutputType) {
        this.taskOutputType = taskOutputType;
    }

    public void setTaskOutputLocation(String taskOutputLocation) {
        this.taskOutputLocation = taskOutputLocation;
    }

    public void setSqlOutputType(String sqlOutputType) {
        this.sqlOutputType = sqlOutputType;
    }

    public void setSqlOutputLocation(String sqlOutputLocation) {
        this.sqlOutputLocation = sqlOutputLocation;
    }
}
