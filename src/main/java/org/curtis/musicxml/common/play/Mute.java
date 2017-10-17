package org.curtis.musicxml.common.play;

public class Mute extends PlayType {
    private MuteType value;

    public Mute() {

    }

    public MuteType getValue() {
        return value;
    }

    public void setValue(MuteType value) {
        this.value = value;
    }
}
