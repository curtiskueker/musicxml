package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fingernails")
public class Fingernails extends Technical {
    public Fingernails() {

    }
}
