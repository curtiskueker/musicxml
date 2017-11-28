package org.curtis.musicxml.note;

import org.curtis.musicxml.common.TextFormatting;

public class AccidentalText {
    private AccidentalType accidentalType;
    private TextFormatting textFormatting;

    public AccidentalText() {

    }

    public AccidentalType getAccidentalType() {
        return accidentalType;
    }

    public void setAccidentalType(AccidentalType accidentalType) {
        this.accidentalType = accidentalType;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public void setTextFormatting(TextFormatting textFormatting) {
        this.textFormatting = textFormatting;
    }
}
