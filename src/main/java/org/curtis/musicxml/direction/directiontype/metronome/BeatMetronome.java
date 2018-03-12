package org.curtis.musicxml.direction.directiontype.metronome;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("beat metronome")
public class BeatMetronome extends Metronome {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beat_unit_1_id")
    private BeatUnit beatUnit1;
    @Transient
    private PerMinute perMinute;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beat_unit_2_id")
    private BeatUnit beatUnit2;

    public BeatMetronome() {

    }

    public BeatUnit getBeatUnit1() {
        return beatUnit1;
    }

    public void setBeatUnit1(BeatUnit beatUnit1) {
        this.beatUnit1 = beatUnit1;
    }

    public PerMinute getPerMinute() {
        return perMinute;
    }

    public void setPerMinute(PerMinute perMinute) {
        this.perMinute = perMinute;
    }

    public BeatUnit getBeatUnit2() {
        return beatUnit2;
    }

    public void setBeatUnit2(BeatUnit beatUnit2) {
        this.beatUnit2 = beatUnit2;
    }
}
