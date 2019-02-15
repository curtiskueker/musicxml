package org.curtis.ui.input;

public class ToDatabase extends ToInput {
    public ToDatabase() {
        setup();
    }

    private void setup() {
        setTitle("Write To Database: ");

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Score Name: ");
        inputRow1.setInputType(InputType.INPUT);
        inputRow1.setInputSize(InputRow.LARGE_INPUT_SIZE);
        inputRow1.setName("scoreName");
        getInputRows().add(inputRow1);
    }
}
