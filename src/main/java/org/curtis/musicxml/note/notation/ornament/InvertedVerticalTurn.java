package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("inverted vertical turn")
public class InvertedVerticalTurn extends PlacedTrillSound {
    public InvertedVerticalTurn() {

    }
}
