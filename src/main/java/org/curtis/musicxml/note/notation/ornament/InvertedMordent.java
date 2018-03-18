package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("inverted mordent")
public class InvertedMordent extends AbstractMordent {
    public InvertedMordent() {

    }
}
