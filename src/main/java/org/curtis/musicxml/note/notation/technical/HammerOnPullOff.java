package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;

public abstract class HammerOnPullOff extends Technical {
    private String value;
    private String type;
    private Integer number = 1;
    private PrintStyle printStyle;
    private String placement;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
