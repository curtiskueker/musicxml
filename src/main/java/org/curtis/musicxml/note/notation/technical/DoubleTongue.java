package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("double tongue")
public class DoubleTongue extends Technical {
    public DoubleTongue() {

    }
}
