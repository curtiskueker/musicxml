package org.curtis.musicxml.common;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("display text")
public class DisplayText extends TextDisplay {
    public DisplayText() {

    }
}
