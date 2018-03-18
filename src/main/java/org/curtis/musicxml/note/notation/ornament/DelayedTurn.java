package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("delayed turn")
public class DelayedTurn extends HorizontalTurn {
    public DelayedTurn() {

    }
}
