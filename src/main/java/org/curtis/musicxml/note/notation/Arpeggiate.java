package org.curtis.musicxml.note.notation;

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
@DiscriminatorValue("arpeggiate")
public class Arpeggiate extends Notation {
    @Column(name = "notation_number")
    private Integer number;
    @Enumerated(EnumType.STRING)
    @Column
    private SymbolDirection direction;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
