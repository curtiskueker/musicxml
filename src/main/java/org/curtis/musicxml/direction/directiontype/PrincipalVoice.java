package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.Connection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("principal voice")
public class PrincipalVoice extends DirectionType {
    @Transient
    private String principalVoice;
    @Transient
    private Connection type;
    @Transient
    private PrincipalVoiceSymbol symbol;
    @Transient
    private PrintStyleAlign printStyleAlign;

    public PrincipalVoice() {

    }

    public String getPrincipalVoice() {
        return principalVoice;
    }

    public void setPrincipalVoice(String principalVoice) {
        this.principalVoice = principalVoice;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public PrincipalVoiceSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(PrincipalVoiceSymbol symbol) {
        this.symbol = symbol;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
