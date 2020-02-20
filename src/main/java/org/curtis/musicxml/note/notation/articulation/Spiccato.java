package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("spiccato")
public class Spiccato extends Articulation {
    public Spiccato() {

    }
}
