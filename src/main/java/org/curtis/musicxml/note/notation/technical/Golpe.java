package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("golpe")
public class Golpe extends Technical {
    public Golpe() {

    }
}
