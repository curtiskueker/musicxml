package org.curtis.musicxml.common;

public class FormattedText {
    private String value;
    private TextFormatting textFormatting;

    public FormattedText() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public void setTextFormatting(TextFormatting textFormatting) {
        this.textFormatting = textFormatting;
    }
}
