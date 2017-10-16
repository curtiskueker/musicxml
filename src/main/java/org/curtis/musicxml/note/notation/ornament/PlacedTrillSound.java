package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.PrintStyle;

public abstract class PlacedTrillSound extends Ornament {
    private PrintStyle printStyle;
    private String placement;
    private TrillSound trillSound;

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

    public TrillSound getTrillSound() {
        return trillSound;
    }

    public void setTrillSound(TrillSound trillSound) {
        this.trillSound = trillSound;
    }
}
