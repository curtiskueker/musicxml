package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.display.Valign;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("non arpeggiate")
public class NonArpeggiate extends Notation {
    @Enumerated(EnumType.STRING)
    @Column(name = "type_value")
    private Valign type;
    @Column(name = "notation_number")
    private Integer number;

    public NonArpeggiate() {

    }

    public Valign getType() {
        return type;
    }

    public void setType(Valign type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
