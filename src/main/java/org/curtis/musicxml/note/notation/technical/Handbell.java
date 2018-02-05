package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public class Handbell extends Technical {
    private HandbellType handbellType;
    private PrintStyle printStyle;
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
