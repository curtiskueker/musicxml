package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.common.PrintStyle;

public class BreathMark extends Articulation {
    private String breathMarkValue;
    private PrintStyle printStyle;
    private String placement;

    public BreathMark() {

    }

    public String getBreathMarkValue() {
        return breathMarkValue;
    }

    public void setBreathMarkValue(String breathMarkValue) {
        this.breathMarkValue = breathMarkValue;
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
