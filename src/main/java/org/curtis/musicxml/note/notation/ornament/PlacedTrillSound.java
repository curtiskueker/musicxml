package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public abstract class PlacedTrillSound extends Ornament {
    private PrintStyle printStyle;
    private Location placement;
    private TrillSound trillSound;

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

    public TrillSound getTrillSound() {
        return trillSound;
    }

    public void setTrillSound(TrillSound trillSound) {
        this.trillSound = trillSound;
    }
}
