package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;

public class DegreeAlter {
    // TODO: semitones
    private PrintStyle printStyle;
    private Boolean plusMinus;

    public DegreeAlter() {

    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Boolean getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Boolean plusMinus) {
        this.plusMinus = plusMinus;
    }
}
