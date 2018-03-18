package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("mordent")
public class Mordent extends AbstractMordent {
    public Mordent() {

    }
}
