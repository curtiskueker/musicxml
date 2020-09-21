package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.converter.SymbolDirectionConverter;
import org.curtis.musicxml.display.SymbolDirection;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("strong accent")
public class StrongAccent extends Articulation {
    @Convert(converter = SymbolDirectionConverter.class)
    @Column
    private SymbolDirection type = SymbolDirection.UP;

    public StrongAccent() {

    }

    public SymbolDirection getType() {
        return type;
    }

    public void setType(SymbolDirection type) {
        this.type = type;
    }
}
