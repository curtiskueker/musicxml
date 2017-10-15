package org.curtis.musicxml.note;

import org.curtis.musicxml.common.PrintStyle;

public class PlacementText {
    private String value;
    private PrintStyle printStyle;
    private String placement;

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

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
