package org.curtis.musicxml.note;

import org.curtis.musicxml.common.FormattedText;

import java.util.ArrayList;
import java.util.List;

public class NoteheadText {
    private List<FormattedText> displayTextList = new ArrayList<>();
    private List<AccidentalText> accidentalTextList = new ArrayList<>();

    public NoteheadText() {

    }

    public List<FormattedText> getDisplayTextList() {
        return displayTextList;
    }

    public void setDisplayTextList(List<FormattedText> displayTextList) {
        this.displayTextList = displayTextList;
    }

    public List<AccidentalText> getAccidentalTextList() {
        return accidentalTextList;
    }

    public void setAccidentalTextList(List<AccidentalText> accidentalTextList) {
        this.accidentalTextList = accidentalTextList;
    }
}
