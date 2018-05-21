package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class PlacedTrillSound extends Ornament {
    @Transient
    private PrintStyle printStyle;
    @Enumerated(EnumType.STRING)
    @Column
    private Location placement;
    @Transient
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
