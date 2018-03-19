package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Line;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("doit")
public class Doit extends Articulation {
    @Transient
    private Line line;

    public Doit() {

    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
