package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("delayed inverted turn")
public class DelayedInvertedTurn extends HorizontalTurn {
    public DelayedInvertedTurn() {

    }
}
