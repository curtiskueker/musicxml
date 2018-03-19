package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("hammer on")
public class HammerOn extends HammerOnPullOff {
    public HammerOn() {

    }
}
