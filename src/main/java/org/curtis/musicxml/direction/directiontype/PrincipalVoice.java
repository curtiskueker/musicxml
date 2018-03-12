package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.Connection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("principal voice")
public class PrincipalVoice extends DirectionType {
    @Column(name = "principal_voice")
    private String principalVoice;
    @Transient
    private Connection type;
    @Enumerated(EnumType.STRING)
    @Column
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
