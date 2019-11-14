package org.curtis.ui.swing.input;

import java.util.List;

public class InputRowFactory {
    private InputRowFactory() {

    }

    public static InputRow newInput(String name, String text, int size, String value) {
        InputRow inputRow = newInput(name, text, size);
        inputRow.setValue(value);

        return inputRow;
    }

    public static InputRow newInput(String name, String text, int size) {
        InputRow inputRow = new InputRow();
        inputRow.setText(text);
        inputRow.setInputType(InputType.INPUT);
        inputRow.setInputSize(size);
        inputRow.setName(name);

        return inputRow;
    }

    public static InputRow newSelection(String name, String text, List<String> selections) {
        InputRow inputRow = new InputRow();
        inputRow.setText(text);
        inputRow.setInputType(InputType.SELECTION);
        inputRow.setSelectionList(selections);
        inputRow.setName(name);

        return inputRow;
    }

    public static InputRow newInputFile(String name, String text, String extension) {
        InputRow inputRow = new InputRow();
        inputRow.setText(text);
        inputRow.setInputType(InputType.INPUT_FILE);
        inputRow.setName(name);
        inputRow.setValue(extension);

        return inputRow;
    }

    public static InputRow newSelectedInputFile(String name, String text, String selectedFilename) {
        InputRow inputRow = newInputFile(name, text, "");
        inputRow.setSelectedFilename(selectedFilename);

        return inputRow;
    }

    public static InputRow newCheckbox(String name, String text) {
        InputRow inputRow = new InputRow();
        inputRow.setText(text);
        inputRow.setInputType(InputType.CHECKBOX);
        inputRow.setName(name);

        return inputRow;
    }

    public static InputRow newDirectory(String name, String text) {
        InputRow inputRow = new InputRow();
        inputRow.setText(text);
        inputRow.setInputType(InputType.DIRECTORY);
        inputRow.setName(name);

        return inputRow;
    }

    public static InputRow newLabel(String text) {
        InputRow inputRow = new InputRow();
        inputRow.setInputType(InputType.LABEL);
        inputRow.setValue(text);

        return inputRow;
    }

    public static InputRow newBoldLabel(String text) {
        InputRow inputRow = newLabel(text);
        inputRow.setBoldText(true);

        return inputRow;
    }

    public static InputRow newButton(String text) {
        InputRow inputRow = new InputRow();
        inputRow.setInputType(InputType.BUTTON);
        inputRow.setName(text);

        return inputRow;
    }
}
