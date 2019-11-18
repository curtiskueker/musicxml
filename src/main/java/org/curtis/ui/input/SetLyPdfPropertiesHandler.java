package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.bin.SetProperties;
import org.curtis.properties.AppProperties;
import org.curtis.ui.task.TaskInitializer;

public class SetLyPdfPropertiesHandler extends InputHandler {
    public SetLyPdfPropertiesHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(AppProperties.getOptionalProperty("musicxml.database.username"));
        setProperties.setPassword(AppProperties.getOptionalProperty("musicxml.database.password"));
        setProperties.setDatabaseName(AppProperties.getOptionalProperty("musicxml.database.name"));
        setProperties.setServer(AppProperties.getOptionalProperty("musicxml.database.server"));
        setProperties.setDatabaseType(AppProperties.getOptionalProperty("musicxml.database.type"));
        setProperties.setLilypondLocation(taskInitializer.getText("lilypondLocation"));
        setProperties.setPdfReaderLocation(taskInitializer.getText("pdfReaderLocation"));

        return setProperties;
    }
}
