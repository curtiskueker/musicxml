package org.curtis.ui.swing.input;

public class InputRowFactory {
    private InputRowFactory() {

    }

    public static InputRow newTitleLable(String text) {
        InputRow inputRow = new InputRow();
        inputRow.setInputType(InputType.LABEL);
        inputRow.setBoldText(true);
        inputRow.setValue(text);

        return inputRow;
    }

    public static InputRow newButton(String text) {
        InputRow inputRow = new InputRow();
        inputRow.setInputType(InputType.BUTTON);
        inputRow.setName(text);

        return inputRow;
    }
}
