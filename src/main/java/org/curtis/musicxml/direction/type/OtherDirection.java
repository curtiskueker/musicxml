package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.PrintStyleAlign;

public class OtherDirection extends DirectionType {
    // TODO: value
    private Boolean printObject;
    private PrintStyleAlign printStyleAlign;

    public OtherDirection() {

    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
