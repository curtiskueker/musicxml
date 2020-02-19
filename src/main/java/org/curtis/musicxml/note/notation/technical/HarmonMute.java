package org.curtis.musicxml.note.notation.technical;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("harmon mute")
public class HarmonMute extends Technical {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "harmon_closed_id")
    private HarmonClosed harmonClosed;

    public HarmonMute() {

    }

    public HarmonClosed getHarmonClosed() {
        return harmonClosed;
    }

    public void setHarmonClosed(HarmonClosed harmonClosed) {
        this.harmonClosed = harmonClosed;
    }
}
