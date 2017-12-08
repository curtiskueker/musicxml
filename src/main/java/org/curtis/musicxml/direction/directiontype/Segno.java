package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;

public class Segno extends DirectionType {
    private PrintStyleAlign printStyleAlign;

    public Segno() {

    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
