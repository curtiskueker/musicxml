package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Line;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("falloff")
public class Falloff extends Articulation {
    @Transient
    private Line line;

    public Falloff() {

    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
