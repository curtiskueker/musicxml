package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("open string")
public class OpenString extends Technical {
    public OpenString() {

    }
}
