package org.curtis.musicxml.note.lyric;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("laughing")
public class Laughing extends LyricItem {
    public Laughing() {

    }
}
