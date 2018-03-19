package org.curtis.musicxml.note.lyric;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("humming")
public class Humming extends LyricItem {
    public Humming() {

    }
}
