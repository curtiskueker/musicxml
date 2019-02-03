package org.curtis.musicxml.bin;

import org.curtis.exception.FileException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;

public class SetProperties extends MusicXmlScript {
    private String username;
    private String password;
    private String databaseName;
    private String server;
    private String lilypondLocation;
    private String pdfReaderLocation;

    public SetProperties() {

    }

    public void execute() throws MusicXmlException {
        // write properties to file
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getPropertyString("musicxml.database.username", username));
        stringBuilder.append(getPropertyString("musicxml.database.password", password));
        stringBuilder.append(getPropertyString("musicxml.database.name", databaseName));
        stringBuilder.append(getPropertyString("musicxml.database.server", server));
        stringBuilder.append(getPropertyString("location.lilypond", lilypondLocation));
        stringBuilder.append(getPropertyString("location.pdfreader", pdfReaderLocation));

        try {
            FileUtil.stringToFile(stringBuilder.toString(), MusicXmlUtil.PROPERTIES_FILENAME + ".properties");
        } catch (FileException e) {
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

    public void setLilypondLocation(String lilypondLocation) {
        this.lilypondLocation = lilypondLocation;
    }

    public void setPdfReaderLocation(String pdfReaderLocation) {
        this.pdfReaderLocation = pdfReaderLocation;
    }
}
