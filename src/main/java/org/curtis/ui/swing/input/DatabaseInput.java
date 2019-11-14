package org.curtis.ui.swing.input;

public class DatabaseInput extends FromInput {
    public DatabaseInput() {
        setup();
    }

    private void setup() {
        getInputRows().add(InputRowFactory.newCheckbox("testDatabase", "Test Database Connection: "));
        getInputRows().add(InputRowFactory.newCheckbox("createDatabase", "Create Database Tables: "));
        getInputRows().add(InputRowFactory.newCheckbox("generateSchema", "Generate Schema File: "));
        getInputRows().add(InputRowFactory.newDirectory("schemaDirectory", "Schema File Directory: "));
        getInputRows().add(InputRowFactory.newInput("schemaFile", "Schema Filename (.sql): ", InputRow.SMALL_INPUT_SIZE));
    }
}
