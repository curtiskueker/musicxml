package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.PrintStyle;

public class AccidentalMark extends Notation {
    // TODO: accidental value
    private PrintStyle printStyle;
    private String placement;

    public AccidentalMark() {

    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
