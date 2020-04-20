package org.curtis.musicxml.display;

import org.curtis.musicxml.display.TextDisplay;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("display text")
public class DisplayText extends TextDisplay {
    public DisplayText() {

    }
}
