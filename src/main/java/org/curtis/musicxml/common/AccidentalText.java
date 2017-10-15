package org.curtis.musicxml.common;

public class AccidentalText {
    private String accidentalValue;
    private TextFormatting textFormatting;

    public AccidentalText() {

    }

    public String getAccidentalValue() {
        return accidentalValue;
    }

    public void setAccidentalValue(String accidentalValue) {
        this.accidentalValue = accidentalValue;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public void setTextFormatting(TextFormatting textFormatting) {
        this.textFormatting = textFormatting;
    }
}
