package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("toe")
public class Toe extends HeelToe {
    public Toe() {

    }
}
