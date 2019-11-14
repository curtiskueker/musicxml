package org.curtis.ui.swing.input;

import org.curtis.properties.AppProperties;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.StringUtil;

public class PropertiesInput extends FromInput {
    public PropertiesInput() {
        setup();
    }

    private void setup() {
        AppProperties.addLocalPropertiesBundle();

        getInputRows().add(InputRowFactory.newInput("username", "Username: ", InputRow.SMALL_INPUT_SIZE, AppProperties.getOptionalProperty("musicxml.database.username")));
        getInputRows().add(InputRowFactory.newInput("password", "Password: ", InputRow.SMALL_INPUT_SIZE, AppProperties.getOptionalProperty("musicxml.database.password")));
        getInputRows().add(InputRowFactory.newInput("databaseName", "Database Name: ", InputRow.SMALL_INPUT_SIZE, AppProperties.getOptionalProperty("musicxml.database.name")));
        String server = AppProperties.getOptionalProperty("musicxml.database.server");
        if (StringUtil.isEmpty(server)) server = "localhost";
        getInputRows().add(InputRowFactory.newInput("server", "Server: ", InputRow.SMALL_INPUT_SIZE, server));
        getInputRows().add(InputRowFactory.newSelection("databaseType", "Database Type: ", TaskConstants.DATABASE_TYPES));
        getInputRows().add(InputRowFactory.newSelectedInputFile("lilypondLocation", "Lilypond Location: ", AppProperties.getOptionalProperty("location.lilypond")));
        getInputRows().add(InputRowFactory.newSelectedInputFile("pdfReaderLocation", "PDF Reader Location: ", AppProperties.getOptionalProperty("location.pdfreader")));
    }
}
