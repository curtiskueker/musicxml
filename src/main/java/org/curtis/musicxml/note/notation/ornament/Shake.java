package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("shake")
public class Shake extends PlacedTrillSound {
    public Shake() {

    }
}
