package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;

public class Handbell extends Technical {
    private String handbellValue;
    private PrintStyle printStyle;
    private String placement;

    public Handbell() {

    }

    public String getHandbellValue() {
        return handbellValue;
    }

    public void setHandbellValue(String handbellValue) {
        this.handbellValue = handbellValue;
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
