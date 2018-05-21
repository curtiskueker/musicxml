package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("handbell")
public class Handbell extends Technical {
    @Enumerated(EnumType.STRING)
    @Column
    private HandbellType handbellType;
    @Transient
    private PrintStyle printStyle;
    @Enumerated(EnumType.STRING)
    @Column
    private Location placement;

    public Handbell() {

    }

    public HandbellType getHandbellType() {
        return handbellType;
    }

    public void setHandbellType(HandbellType handbellType) {
        this.handbellType = handbellType;
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
