package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.display.SymbolDirection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("strong accent")
public class StrongAccent extends Articulation {
    @Enumerated(EnumType.STRING)
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
