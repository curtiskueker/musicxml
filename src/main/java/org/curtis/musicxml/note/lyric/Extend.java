package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.PrintStyle;

public class Extend extends LyricItem {
    private String type;
    private PrintStyle printStyle;

    public Extend() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
