package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("down bow")
public class DownBow extends Technical {
    public DownBow() {

    }
}
