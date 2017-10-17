package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Connection;

public class Extend extends LyricItem {
    private Connection type;
    private PrintStyle printStyle;

    public Extend() {

    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
