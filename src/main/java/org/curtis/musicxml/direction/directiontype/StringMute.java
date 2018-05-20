package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("string mute")
public class StringMute extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column
    private StringMuteDirection type;
    @Transient
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
