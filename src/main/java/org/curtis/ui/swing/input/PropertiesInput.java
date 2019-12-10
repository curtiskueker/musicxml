package org.curtis.ui.swing.input;

import org.curtis.database.DBConstants;
import org.curtis.properties.AppProperties;
import org.curtis.properties.PropertiesConstants;
import org.curtis.util.StringUtil;

public class PropertiesInput extends FromInput {
    public PropertiesInput() {
        setup();
    }

    private void setup() {
        AppProperties.addLocalPropertiesBundle();

        String prefix = PropertiesConstants.PREFIX + ".";
        getInputRows().add(InputRowFactory.newInput("username", "Username: ", InputRow.SMALL_INPUT_SIZE, AppProperties.getOptionalProperty(prefix  + PropertiesConstants.DB_USERNAME)));
        getInputRows().add(InputRowFactory.newInput("password", "Password: ", InputRow.SMALL_INPUT_SIZE, AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_PASSWORD)));
        getInputRows().add(InputRowFactory.newInput("databaseName", "Database Name: ", InputRow.SMALL_INPUT_SIZE, AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_NAME)));
        String server = AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_SERVER);
        if (StringUtil.isEmpty(server)) server = "localhost";
        getInputRows().add(InputRowFactory.newInput("server", "Server: ", InputRow.SMALL_INPUT_SIZE, server));
        getInputRows().add(InputRowFactory.newSelection("databaseType", "Database Type: ", DBConstants.DATABASE_TYPES, AppProperties.getOptionalProperty(prefix + PropertiesConstants.DB_TYPE)));
        getInputRows().add(InputRowFactory.newSelectedInputFile("lilypondLocation", "Lilypond Location: ", AppProperties.getOptionalProperty(PropertiesConstants.LILYPOND_LOCATION)));
        getInputRows().add(InputRowFactory.newSelectedInputFile("pdfReaderLocation", "PDF Reader Location: ", AppProperties.getOptionalProperty(PropertiesConstants.PDF_LOCATION)));
    }
}
