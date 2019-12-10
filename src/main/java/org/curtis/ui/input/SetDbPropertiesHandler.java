package org.curtis.ui.input;

import org.curtis.musicxml.bin.DatabaseExec;
import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.bin.SetDbProperties;
import org.curtis.musicxml.bin.SetProperties;
import org.curtis.properties.AppProperties;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.task.TaskInitializer;

public class SetDbPropertiesHandler extends InputHandler {
    public SetDbPropertiesHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(taskInitializer.getText("username"));
        setProperties.setPassword(taskInitializer.getText("password"));
        setProperties.setDatabaseName(taskInitializer.getText("databaseName"));
        setProperties.setServer(taskInitializer.getText("server"));
        setProperties.setDatabaseType(taskInitializer.getSelection("databaseType"));
        setProperties.setLilypondLocation(AppProperties.getOptionalProperty(PropertiesConstants.LILYPOND_LOCATION));
        setProperties.setPdfReaderLocation(AppProperties.getOptionalProperty(PropertiesConstants.PDF_LOCATION));

        DatabaseExec databaseExec = new DatabaseExec();
        databaseExec.setTestDatabase(taskInitializer.isSelected("testDatabase"));

        SetDbProperties setDbProperties = new SetDbProperties();
        setDbProperties.setSetProperties(setProperties);
        setDbProperties.setDatabaseExec(databaseExec);

        return setDbProperties;
    }
}
