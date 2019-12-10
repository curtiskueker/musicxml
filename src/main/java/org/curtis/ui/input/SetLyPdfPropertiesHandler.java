package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.bin.SetProperties;
import org.curtis.properties.AppProperties;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.task.TaskInitializer;

public class SetLyPdfPropertiesHandler extends InputHandler {
    public SetLyPdfPropertiesHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        String prefix = PropertiesConstants.PREFIX + ".";

        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_USERNAME));
        setProperties.setPassword(AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_PASSWORD));
        setProperties.setDatabaseName(AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_NAME));
        setProperties.setServer(AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_SERVER));
        setProperties.setDatabaseType(AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_TYPE));
        setProperties.setLilypondLocation(taskInitializer.getText("lilypondLocation"));
        setProperties.setPdfReaderLocation(taskInitializer.getText("pdfReaderLocation"));

        return setProperties;
    }
}
