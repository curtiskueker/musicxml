package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.SymbolDirection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("strong accent")
public class StrongAccent extends Articulation {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Enumerated(EnumType.STRING)
    @Column
    private SymbolDirection type = SymbolDirection.UP;

    public StrongAccent() {

    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public SymbolDirection getType() {
        return type;
    }

    public void setType(SymbolDirection type) {
        this.type = type;
    }
}
