package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public class BreathMark extends Articulation {
    private BreathMarkType breathMarkValue;
    private PrintStyle printStyle;
    private Location placement;

    public BreathMark() {

    }

    public BreathMarkType getBreathMarkValue() {
        return breathMarkValue;
    }

    public void setBreathMarkValue(BreathMarkType breathMarkValue) {
        this.breathMarkValue = breathMarkValue;
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
