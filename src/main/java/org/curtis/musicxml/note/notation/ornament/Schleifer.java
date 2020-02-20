package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("schleifer")
public class Schleifer extends Ornament {
    public Schleifer() {

    }
}
