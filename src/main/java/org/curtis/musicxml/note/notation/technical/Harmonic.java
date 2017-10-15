package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;

public class Harmonic extends Technical {
    // TODO: natural
    // TODO: artificial
    // TODO: base pitch
    // TODO: touching pitch
    // TODO: sounding pitch
    private Boolean printObject;
    private PrintStyle printStyle;
    private String placement;

    public Harmonic() {

    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
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
