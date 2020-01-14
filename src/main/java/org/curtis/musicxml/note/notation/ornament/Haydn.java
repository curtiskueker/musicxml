package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("haydn")
public class Haydn extends PlacedTrillSound {
    public Haydn() {

    }
}
