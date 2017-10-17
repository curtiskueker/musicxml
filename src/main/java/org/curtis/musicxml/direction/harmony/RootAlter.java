package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import java.math.BigDecimal;

public class RootAlter {
    private BigDecimal semitones;
    private Boolean printObject;
    private PrintStyle printStyle;
    private Location location;

    public RootAlter() {

    }

    public BigDecimal getSemitones() {
        return semitones;
    }

    public void setSemitones(BigDecimal semitones) {
        this.semitones = semitones;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
