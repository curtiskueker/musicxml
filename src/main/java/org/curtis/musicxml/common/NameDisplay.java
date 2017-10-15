package org.curtis.musicxml.common;

import java.util.List;

public class NameDisplay {
    private List<FormattedText> displayTextList;
    private List<AccidentalText> accidentalTextList;

    public NameDisplay() {

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
