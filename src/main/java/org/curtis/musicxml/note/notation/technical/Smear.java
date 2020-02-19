package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("smear")
public class Smear extends Technical {
    public Smear() {

    }
}
