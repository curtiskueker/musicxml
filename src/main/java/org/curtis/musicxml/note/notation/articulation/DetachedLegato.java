package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("detached legato")
public class DetachedLegato extends Articulation {
    public DetachedLegato() {

    }
}
