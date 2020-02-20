package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("falloff")
public class Falloff extends LinedArticulation {
    public Falloff() {

    }
}
