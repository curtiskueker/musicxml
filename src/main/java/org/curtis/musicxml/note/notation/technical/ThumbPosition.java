package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("thumb position")
public class ThumbPosition extends Technical {
    public ThumbPosition() {

    }
}
