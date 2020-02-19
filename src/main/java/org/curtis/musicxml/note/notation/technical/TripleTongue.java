package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("triple tongue")
public class TripleTongue extends Technical {
    public TripleTongue() {

    }
}
