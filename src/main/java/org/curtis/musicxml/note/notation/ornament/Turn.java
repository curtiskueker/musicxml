package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("turn")
public class Turn extends HorizontalTurn {
    public Turn() {

    }
}
