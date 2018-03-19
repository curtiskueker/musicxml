package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("heel")
public class Heel extends HeelToe {
    public Heel() {

    }
}
