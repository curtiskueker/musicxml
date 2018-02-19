package org.curtis.musicxml.common;

import org.curtis.musicxml.note.AccidentalText;

import java.util.ArrayList;
import java.util.List;

public class NameDisplay {
    private List<FormattedText> displayTextList = new ArrayList<>();
    private List<AccidentalText> accidentalTextList = new ArrayList<>();
    private Boolean printObject;

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

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
