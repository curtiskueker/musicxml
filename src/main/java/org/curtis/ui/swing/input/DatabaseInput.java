package org.curtis.ui.swing.input;

public class DatabaseInput extends FromInput {
    public DatabaseInput() {
        setup();
    }

    private void setup() {
        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Test Database Connection: ");
        inputRow1.setInputType(InputType.CHECKBOX);
        inputRow1.setName("testDatabase");
        getInputRows().add(inputRow1);

        InputRow inputRow2 = new InputRow();
        inputRow2.setText("Create Database Tables: ");
        inputRow2.setInputType(InputType.CHECKBOX);
        inputRow2.setName("createDatabase");
        getInputRows().add(inputRow2);

        InputRow inputRow3 = new InputRow();
        inputRow3.setText("Generate Schema File: ");
        inputRow3.setInputType(InputType.CHECKBOX);
        inputRow3.setName("generateSchema");
        getInputRows().add(inputRow3);

        InputRow inputRow4 = new InputRow();
        inputRow4.setText("Schema File Directory: ");
        inputRow4.setInputType(InputType.OUTPUT_DIRECTORY);
        inputRow4.setName("schemaDirectory");
        getInputRows().add(inputRow4);

        InputRow inputRow5 = new InputRow();
        inputRow5.setText("Schema Filename (.sql): ");
        inputRow5.setInputType(InputType.INPUT);
        inputRow5.setInputSize(InputRow.SMALL_INPUT_SIZE);
        inputRow5.setName("schemaFile");
        getInputRows().add(inputRow5);
    }
}
