package org.curtis.musicxml.direction.directiontype.metronome;

public class BeatMetronome extends Metronome {
    private BeatUnit beatUnit1;
    private PerMinute perMinute;
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
