package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.PrintStyleAlign;

public class Coda extends DirectionType {
    private PrintStyleAlign printStyleAlign;

    public Coda() {

    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
