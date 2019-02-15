package org.curtis.ui.input;

public class InputRow {
    private String text = "";
    private InputType inputType = InputType.NONE;
    private String name = "";
    private String value = "";
    private String selectedFilename = "";
    private boolean isBoldText = false;
    private int inputSize = SMALL_INPUT_SIZE;
    private Object[] selectionList = {};

    public static final int SMALL_INPUT_SIZE = 150;
    public static final int LARGE_INPUT_SIZE = 300;

    public InputRow() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSelectedFilename() {
        return selectedFilename;
    }

    public void setSelectedFilename(String selectedFilename) {
        this.selectedFilename = selectedFilename;
    }

    public boolean isBoldText() {
        return isBoldText;
    }

    public void setBoldText(boolean boldText) {
        isBoldText = boldText;
    }

    public int getInputSize() {
        return inputSize;
    }

    public void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }

    public Object[] getSelectionList() {
        return selectionList;
    }

    public void setSelectionList(Object[] selectionList) {
        this.selectionList = selectionList;
    }
}
