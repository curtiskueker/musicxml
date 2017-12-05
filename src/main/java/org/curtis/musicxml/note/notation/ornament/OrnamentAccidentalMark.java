package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.notation.AccidentalMark;

public class OrnamentAccidentalMark extends Ornament {
    private AccidentalMark accidentalMark;

    public OrnamentAccidentalMark() {

    }

    public AccidentalMark getAccidentalMark() {
        return accidentalMark;
    }

    public void setAccidentalMark(AccidentalMark accidentalMark) {
        this.accidentalMark = accidentalMark;
    }
}
