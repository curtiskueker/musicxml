package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.PrintStyle;

public abstract class EmptyTrillSound extends Ornament {
    private PrintStyle printStyle;
    private String placement;
    // TODO: trill sound

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
