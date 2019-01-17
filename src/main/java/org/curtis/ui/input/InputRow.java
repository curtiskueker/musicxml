package org.curtis.ui.input;

public class InputRow {
    private String text = "";
    private InputType inputType = InputType.NONE;
    private String name = "";
    private String value = "";
    private String selectedFilename = "";

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
}
