package org.curtis.musicxml.note;

import org.curtis.musicxml.common.TextFormatting;

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
