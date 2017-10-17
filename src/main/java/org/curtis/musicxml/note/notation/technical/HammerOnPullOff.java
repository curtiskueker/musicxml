package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Connection;

public abstract class HammerOnPullOff extends Technical {
    private String value;
    private Connection type;
    private Integer number = 1;
    private PrintStyle printStyle;
    private Location placement;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
