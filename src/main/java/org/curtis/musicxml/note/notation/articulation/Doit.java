package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("doit")
public class Doit extends LinedArticulation {
    public Doit() {

    }
}
