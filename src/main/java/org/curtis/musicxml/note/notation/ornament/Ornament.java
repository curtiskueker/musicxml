package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.notation.AccidentalMark;

import java.util.List;

public abstract class Ornament {
    // TODO: wavy line
    private List<AccidentalMark> accidentalMarks;

    public List<AccidentalMark> getAccidentalMarks() {
        return accidentalMarks;
    }

    public void setAccidentalMarks(List<AccidentalMark> accidentalMarks) {
        this.accidentalMarks = accidentalMarks;
    }
}
