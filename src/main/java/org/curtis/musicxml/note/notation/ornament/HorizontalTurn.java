package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public abstract class HorizontalTurn extends Ornament {
    private PrintStyle printStyle;
    private Location placement;
    private TrillSound trillSound;
    private Boolean slash;

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

    public Boolean getSlash() {
        return slash;
    }

    public void setSlash(Boolean slash) {
        this.slash = slash;
    }
}
