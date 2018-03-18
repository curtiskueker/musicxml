package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("trill mark")
public class TrillMark extends PlacedTrillSound {
    public TrillMark() {

    }
}
