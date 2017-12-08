package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;

public class StringMute extends DirectionType {
    private StringMuteDirection type;
    private PrintStyleAlign printStyleAlign;

    public StringMute() {

    }

    public StringMuteDirection getType() {
        return type;
    }

    public void setType(StringMuteDirection type) {
        this.type = type;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
