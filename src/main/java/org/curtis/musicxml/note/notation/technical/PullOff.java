package org.curtis.musicxml.note.notation.technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("pull off")
public class PullOff extends HammerOnPullOff {
    public PullOff() {

    }
}
