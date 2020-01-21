package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.Connection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("principal voice")
public class PrincipalVoice extends DirectionType {
    @Column(name = "principal_voice")
    private String principalVoice;
    @Enumerated(EnumType.STRING)
    @Column(name = "direction_type")
    private Connection type;
    @Enumerated(EnumType.STRING)
    @Column
    private PrincipalVoiceSymbol symbol;

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
}
