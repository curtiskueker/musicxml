package org.curtis.musicxml.score.instrument;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("solo")
public class Solo extends InstrumentType {
    public Solo() {

    }
}
