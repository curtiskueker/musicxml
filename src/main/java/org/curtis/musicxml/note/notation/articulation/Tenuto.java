package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tenuto")
public class Tenuto extends Articulation {
    public Tenuto() {

    }
}
