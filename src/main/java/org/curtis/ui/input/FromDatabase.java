package org.curtis.ui.input;

public class FromDatabase extends FromInput {
    public FromDatabase() {
        setup();
    }

    private void setup() {
        setTitle("Read From Database: ");

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Score Name: ");
        inputRow1.setInputType(InputType.SCORE_NAME_SELECTION);
        inputRow1.setName("scoreName");
        getInputRows().add(inputRow1);
    }
}
