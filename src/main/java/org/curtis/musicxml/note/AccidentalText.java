package org.curtis.musicxml.note;

import org.curtis.musicxml.common.TextFormatting;

public class AccidentalText {
    private AccidentalValue accidentalValue;
    private TextFormatting textFormatting;

    public AccidentalText() {

    }

    public AccidentalValue getAccidentalValue() {
        return accidentalValue;
    }

    public void setAccidentalValue(AccidentalValue accidentalValue) {
        this.accidentalValue = accidentalValue;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public void setTextFormatting(TextFormatting textFormatting) {
        this.textFormatting = textFormatting;
    }
}
