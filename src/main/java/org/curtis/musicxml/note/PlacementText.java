package org.curtis.musicxml.note;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public class PlacementText {
    private String value;
    private PrintStyle printStyle;
    private Location placement;

    public PlacementText() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
