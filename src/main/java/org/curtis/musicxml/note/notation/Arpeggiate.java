package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.converter.SymbolDirectionConverter;
import org.curtis.musicxml.display.SymbolDirection;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("arpeggiate")
public class Arpeggiate extends Notation {
    @Column(name = "notation_number")
    private Integer number;
    @Convert(converter = SymbolDirectionConverter.class)
    @Column
    private SymbolDirection direction;

    public Arpeggiate() {

    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public SymbolDirection getDirection() {
        return direction;
    }

    public void setDirection(SymbolDirection direction) {
        this.direction = direction;
    }
}
