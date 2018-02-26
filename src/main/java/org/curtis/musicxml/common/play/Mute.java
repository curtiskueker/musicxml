package org.curtis.musicxml.common.play;

public class Mute extends PlayType {
    private MuteType muteType;

    public Mute() {

    }

    public MuteType getMuteType() {
        return muteType;
    }

    public void setMuteType(MuteType muteType) {
        this.muteType = muteType;
    }
}
