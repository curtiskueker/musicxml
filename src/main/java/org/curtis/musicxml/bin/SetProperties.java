package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.exception.FileException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
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

    public SetProperties() {

    }

    public void execute() throws MusicXmlException {
        // write properties to file
        String prefix = PropertiesConstants.PREFIX + ".";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_USERNAME, username));
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_PASSWORD, password));
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_NAME, databaseName));
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_SERVER, server));
        stringBuilder.append(getPropertyString(prefix + PropertiesConstants.DB_TYPE, databaseType));
        stringBuilder.append(getPropertyString(PropertiesConstants.LILYPOND_LOCATION, lilypondLocation));
        stringBuilder.append(getPropertyString(PropertiesConstants.PDF_LOCATION, pdfReaderLocation));

        try {
            FileUtil.stringToFile(stringBuilder.toString(), PropertiesHandler.PROPERTIES_FILENAME + ".properties");
        } catch (FileException e) {
            throw new MusicXmlException(e.getMessage());
        }

        try {
            MusicXmlUtil.clearDb();
            PropertiesHandler.addLocalPropertiesBundle();
        } catch (DBException e) {
            throw new MusicXmlException(e);
        }
    }


    private String getPropertyString(String propertyName, String propertyValue) {
        if (StringUtil.isEmpty(propertyValue)) return "";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(propertyName);
        stringBuilder.append("=");
        stringBuilder.append(propertyValue);
        stringBuilder.append("\n");

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

    public static void main(String[] args) {
        SetProperties setProperties = new SetProperties();
        for (String arg : args) {
            if (arg.startsWith("USERNAME=")) {
                setProperties.setUsername(arg.replace("USERNAME=", ""));
            } else if (arg.startsWith("PASSWORD=")) {
                setProperties.setPassword(arg.replace("PASSWORD=", ""));
            } else if (arg.startsWith("DATABASE=")) {
                setProperties.setDatabaseName(arg.replace("DATABASE=", ""));
            } else if (arg.startsWith("SERVER=")) {
                setProperties.setServer(arg.replace("SERVER=", ""));
            } else if (arg.startsWith("DATABASE_TYPE=")) {
                setProperties.setDatabaseType(arg.replace("DATABASE_TYPE=", ""));
            } else if (arg.startsWith("LILYPOND=")) {
                setProperties.setLilypondLocation(arg.replace("LILYPOND=", ""));
            } else if (arg.startsWith("PDF_READER=")) {
                setProperties.setPdfReaderLocation(arg.replace("PDF_READER=", ""));
            }
        }

        try {
            setProperties.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
