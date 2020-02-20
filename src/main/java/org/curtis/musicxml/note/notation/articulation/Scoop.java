package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("scoop")
public class Scoop extends LinedArticulation {
    public Scoop() {

    }
}
