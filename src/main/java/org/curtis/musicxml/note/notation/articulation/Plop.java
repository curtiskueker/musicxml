package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("plop")
public class Plop extends LinedArticulation {
    public Plop() {

    }
}
