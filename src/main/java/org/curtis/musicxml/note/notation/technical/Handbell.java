package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public class Handbell extends Technical {
    private HandbellType handbellValue;
    private PrintStyle printStyle;
    private Location placement;

    public Handbell() {

    }

    public HandbellType getHandbellValue() {
        return handbellValue;
    }

    public void setHandbellValue(HandbellType handbellValue) {
        this.handbellValue = handbellValue;
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
