package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("soft accent")
public class SoftAccent extends Articulation {
    public SoftAccent() {

    }
}
