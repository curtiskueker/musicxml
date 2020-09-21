package org.curtis.musicxml.common.play;

import org.curtis.musicxml.converter.MuteTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("mute")
public class Mute extends PlayType {
    @Convert(converter = MuteTypeConverter.class)
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
