package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.PrintStyleAlign;

public class StringMute extends DirectionType {
    private String type;
    private PrintStyleAlign printStyleAlign;

    public StringMute() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
