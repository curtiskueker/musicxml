package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.display.Display;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public HarmonMute() {

    }

    public HarmonClosed getHarmonClosed() {
        return harmonClosed;
    }

    public void setHarmonClosed(HarmonClosed harmonClosed) {
        this.harmonClosed = harmonClosed;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
