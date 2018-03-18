package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("vertical turn")
public class VerticalTurn extends PlacedTrillSound {
    public VerticalTurn() {

    }
}
