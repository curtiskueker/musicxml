package org.curtis.musicxml.note;

import org.curtis.musicxml.common.PrintStyle;

public class EmptyPlacement {
    private PrintStyle printStyle;
    private String placement;

    public EmptyPlacement() {

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
