package org.curtis.musicxml.direction.type.percussion;

import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.direction.type.DirectionType;

public abstract class Percussion extends DirectionType {
    private PrintStyleAlign printStyleAlign;
    private String enclosure;

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }
}
