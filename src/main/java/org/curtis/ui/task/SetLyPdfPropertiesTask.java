package org.curtis.ui.task;

import org.curtis.musicxml.bin.SetProperties;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.properties.AppProperties;
import org.curtis.ui.task.exception.TaskException;

public class SetLyPdfPropertiesTask extends MusicXmlTask {
    private String lilypondLocation;
    private String pdfReaderLocation;

    public SetLyPdfPropertiesTask(TaskInitializer taskInitializer) {
        super(taskInitializer);
    }

    public void executeTask() throws TaskException {
        SetProperties setProperties = new SetProperties();
        setProperties.setUsername(AppProperties.getOptionalProperty("musicxml.database.username"));
        setProperties.setPassword(AppProperties.getOptionalProperty("musicxml.database.password"));
        setProperties.setDatabaseName(AppProperties.getOptionalProperty("musicxml.database.name"));
        setProperties.setServer(AppProperties.getOptionalProperty("musicxml.database.server"));
        setProperties.setDatabaseType(AppProperties.getOptionalProperty("musicxml.database.type"));
        setProperties.setLilypondLocation(lilypondLocation);
        setProperties.setPdfReaderLocation(pdfReaderLocation);

        try {
            setProperties.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e);
        }
    }

    public void initialize() {
        lilypondLocation = taskInitializer.getText("lilypondLocation");
        pdfReaderLocation = taskInitializer.getText("pdfReaderLocation");
    }
}
