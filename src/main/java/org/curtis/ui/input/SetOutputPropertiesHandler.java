package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.bin.SetProperties;
import org.curtis.properties.PropertiesConstants;
import org.curtis.properties.PropertiesHandler;
import org.curtis.ui.task.TaskInitializer;

public class SetOutputPropertiesHandler extends InputHandler {
    public SetOutputPropertiesHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        String prefix = PropertiesConstants.PREFIX + ".";

        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_USERNAME));
        setProperties.setPassword(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_PASSWORD));
        setProperties.setDatabaseName(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_NAME));
        setProperties.setServer(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_SERVER));
        setProperties.setDatabaseType(PropertiesHandler.getOptionalProperty(prefix + PropertiesConstants.DB_TYPE));
        setProperties.setLilypondLocation(PropertiesHandler.getOptionalProperty(PropertiesConstants.LILYPOND_LOCATION));
        setProperties.setPdfReaderLocation(PropertiesHandler.getOptionalProperty(PropertiesConstants.PDF_LOCATION));
        setProperties.setTaskOutputType(taskInitializer.getSelection("taskOutputType"));
        setProperties.setTaskOutputLocation(taskInitializer.getText("taskOutputLocation"));
        setProperties.setSqlOutputType(taskInitializer.getSelection("sqlOutputType"));
        setProperties.setSqlOutputLocation(taskInitializer.getText("sqlOutputLocation"));

        return setProperties;
    }
}
