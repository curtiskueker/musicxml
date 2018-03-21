package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Line;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("doit")
public class Doit extends Articulation {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "line_id")
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
