package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.PrintStyleAlign;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("principal voice")
public class PrincipalVoice extends DirectionType {
    @Column(name = "principal_voice")
    private String principalVoice;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Enumerated(EnumType.STRING)
    @Column
    private PrincipalVoiceSymbol symbol;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_align_id")
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
