package org.curtis.ui.swing.input;

public class ToDatabase extends ToInput {
    public ToDatabase() {
        setup();
    }

    private void setup() {
        setTitle("Write To Database: ");

        getInputRows().add(InputRowFactory.newInput("scoreName", "Score Name: ", InputRow.LARGE_INPUT_SIZE));
    }
}
