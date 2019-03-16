package org.curtis.ui.input;

import org.curtis.properties.AppProperties;
import org.curtis.util.StringUtil;

public class PropertiesInput extends FromInput {
    public PropertiesInput() {
        setup();
    }

    private void setup() {
        AppProperties.addLocalPropertiesBundle();

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Username: ");
        inputRow1.setInputType(InputType.INPUT);
        inputRow1.setInputSize(InputRow.SMALL_INPUT_SIZE);
        inputRow1.setName("username");
        inputRow1.setValue(AppProperties.getOptionalProperty("musicxml.database.username"));
        getInputRows().add(inputRow1);

        InputRow inputRow2 = new InputRow();
        inputRow2.setText("Password: ");
        inputRow2.setInputType(InputType.PASSWORD);
        inputRow2.setInputSize(InputRow.SMALL_INPUT_SIZE);
        inputRow2.setName("password");
        inputRow2.setValue(AppProperties.getOptionalProperty("musicxml.database.password"));
        getInputRows().add(inputRow2);

        InputRow inputRow3 = new InputRow();
        inputRow3.setText("Database Name: ");
        inputRow3.setInputType(InputType.INPUT);
        inputRow3.setInputSize(InputRow.SMALL_INPUT_SIZE);
        inputRow3.setName("databaseName");
        inputRow3.setValue(AppProperties.getOptionalProperty("musicxml.database.name"));
        getInputRows().add(inputRow3);

        InputRow inputRow4 = new InputRow();
        inputRow4.setText("Server: ");
        inputRow4.setInputType(InputType.INPUT);
        inputRow4.setInputSize(InputRow.SMALL_INPUT_SIZE);
        inputRow4.setName("server");
        String server = AppProperties.getOptionalProperty("musicxml.database.server");
        if (StringUtil.isEmpty(server)) server = "localhost";
        inputRow4.setValue(server);
        getInputRows().add(inputRow4);

        InputRow inputRow5 = new InputRow();
        inputRow5.setText("Database Type: ");
        inputRow5.setInputType(InputType.SELECTION);
        String[] databaseTypes = {"mysql", "postgresql"};
        inputRow5.setSelectionList(databaseTypes);
        inputRow5.setSelectedItem(AppProperties.getOptionalProperty("musicxml.database.type"));
        inputRow5.setName("databaseType");
        getInputRows().add(inputRow5);

        InputRow inputRow6 = new InputRow();
        inputRow6.setText("Lilypond Location: ");
        inputRow6.setInputType(InputType.INPUT_FILE);
        inputRow6.setName("lilypondLocation");
        inputRow6.setSelectedFilename(AppProperties.getOptionalProperty("location.lilypond"));
        getInputRows().add(inputRow6);

        InputRow inputRow7 = new InputRow();
        inputRow7.setText("PDF Reader Location: ");
        inputRow7.setInputType(InputType.INPUT_FILE);
        inputRow7.setName("pdfReaderLocation");
        inputRow7.setSelectedFilename(AppProperties.getOptionalProperty("location.pdfreader"));
        getInputRows().add(inputRow7);
    }
}
