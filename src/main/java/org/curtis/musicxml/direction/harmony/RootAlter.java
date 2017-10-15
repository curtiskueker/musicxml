package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;

public class RootAlter {
    // TODO: semitones
    private Boolean printObject;
    private PrintStyle printStyle;
    private String location;

    public RootAlter() {

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
