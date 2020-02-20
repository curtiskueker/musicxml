package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("staccato")
public class Staccato extends Articulation {
    public Staccato() {

    }
}
