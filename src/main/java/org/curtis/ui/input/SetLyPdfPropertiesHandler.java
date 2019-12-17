package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.bin.SetProperties;
import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.task.TaskInitializer;

public class SetLyPdfPropertiesHandler extends InputHandler {
    public SetLyPdfPropertiesHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        String prefix = PropertiesConstants.PROPERTIES_PREFIX + ".";

        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_USERNAME));
        setProperties.setPassword(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_PASSWORD));
        setProperties.setDatabaseName(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_NAME));
        setProperties.setServer(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_SERVER));
        setProperties.setDatabaseType(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_TYPE));
        setProperties.setLilypondLocation(taskInitializer.getText("lilypondLocation"));
        setProperties.setPdfReaderLocation(taskInitializer.getText("pdfReaderLocation"));
        setProperties.setTaskOutputType(PropertiesHandler.getOptionalProperty(PropertiesConstants.TASK_OUTPUT_TYPE));
        setProperties.setTaskOutputLocation(PropertiesHandler.getOptionalProperty(PropertiesConstants.TASK_OUTPUT_LOCATION));
        setProperties.setSqlOutputType(PropertiesHandler.getOptionalProperty(PropertiesConstants.SQL_OUTPUT_TYPE));
        setProperties.setSqlOutputLocation(PropertiesHandler.getOptionalProperty(PropertiesConstants.SQL_OUTPUT_LOCATION));

        return setProperties;
    }
}
