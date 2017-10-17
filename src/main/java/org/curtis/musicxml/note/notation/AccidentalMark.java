package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.note.AccidentalValue;

public class AccidentalMark extends Notation {
    private AccidentalValue accidentalValue;
    private PrintStyle printStyle;
    private Location placement;

    public AccidentalMark() {

    }

    public AccidentalValue getAccidentalValue() {
        return accidentalValue;
    }

    public void setAccidentalValue(AccidentalValue accidentalValue) {
        this.accidentalValue = accidentalValue;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }
}
