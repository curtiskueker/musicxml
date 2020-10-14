package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.converter.ConnectionConverter;
import org.curtis.musicxml.converter.PrincipalVoiceSymbolConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("principal voice")
public class PrincipalVoice extends DirectionType {
    @Column
    private String value;
    @Convert(converter = ConnectionConverter.class)
    @Column
    private Connection type;
    @Convert(converter = PrincipalVoiceSymbolConverter.class)
    @Column
    private PrincipalVoiceSymbol symbol;

    public PrincipalVoice() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
