package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("snap pizzicato")
public class SnapPizzicato extends Technical {
    public SnapPizzicato() {

    }
}
