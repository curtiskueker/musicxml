package org.curtis.musicxml.direction.type.percussion;

import org.curtis.musicxml.common.EnclosureShape;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.direction.type.DirectionType;

public abstract class Percussion extends DirectionType {
    private PrintStyleAlign printStyleAlign;
    private EnclosureShape enclosure;

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }

    public EnclosureShape getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EnclosureShape enclosure) {
        this.enclosure = enclosure;
    }
}
