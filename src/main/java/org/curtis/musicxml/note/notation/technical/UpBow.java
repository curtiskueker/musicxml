package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("up bow")
public class UpBow extends Technical {
    public UpBow() {

    }
}
