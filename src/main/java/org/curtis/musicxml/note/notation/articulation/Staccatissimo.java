package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("staccatissimo")
public class Staccatissimo extends Articulation {
    public Staccatissimo() {

    }
}
