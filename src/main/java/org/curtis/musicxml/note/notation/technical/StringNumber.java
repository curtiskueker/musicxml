package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public class StringNumber extends Technical {
    private Integer stringNumber;
    private PrintStyle printStyle;
    private Location placement;

    public StringNumber() {

    }

    public Integer getStringNumber() {
        return stringNumber;
    }

    public void setStringNumber(Integer stringNumber) {
        this.stringNumber = stringNumber;
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
