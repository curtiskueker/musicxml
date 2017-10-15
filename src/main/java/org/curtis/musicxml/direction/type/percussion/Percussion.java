package org.curtis.musicxml.direction.type.percussion;

import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.direction.type.DirectionType;

public abstract class Percussion extends DirectionType {
    private String otherPercussion;
    private PrintStyleAlign printStyleAlign;
    private String enclosure;

    public String getOtherPercussion() {
        return otherPercussion;
    }

    public void setOtherPercussion(String otherPercussion) {
        this.otherPercussion = otherPercussion;
    }

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
