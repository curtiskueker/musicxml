package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;

public class SetDbProperties extends MusicXmlScript {
    private SetProperties setProperties;
    private DatabaseExec databaseExec;

    public SetDbProperties() {

    }

    public void setSetProperties(SetProperties setProperties) {
        this.setProperties = setProperties;
    }

    public void setDatabaseExec(DatabaseExec databaseExec) {
        this.databaseExec = databaseExec;
    }

    public void execute() throws MusicXmlException {
        if (setProperties != null) setProperties.execute();
        if (databaseExec != null) databaseExec.execute();
    }
}
