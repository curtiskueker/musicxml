package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.common.EnclosureShape;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.direction.directiontype.DirectionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("percussion")
public abstract class Percussion extends DirectionType {
    @Transient
    private PrintStyleAlign printStyleAlign;
    @Transient
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
