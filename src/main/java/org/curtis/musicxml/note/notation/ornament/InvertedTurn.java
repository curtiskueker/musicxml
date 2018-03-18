package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("inverted turn")
public class InvertedTurn extends HorizontalTurn {
    public InvertedTurn() {

    }
}
