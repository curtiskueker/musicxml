package org.curtis.musicxml.common.play;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("mute")
public class Mute extends PlayType {
    @Enumerated(EnumType.STRING)
    @Column(name = "value")
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
