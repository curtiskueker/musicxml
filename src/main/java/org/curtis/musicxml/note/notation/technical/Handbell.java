package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("handbell")
public class Handbell extends Technical {
    @Transient
    private HandbellType handbellType;
    @Transient
    private PrintStyle printStyle;
    @Transient
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
