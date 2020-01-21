package org.curtis.musicxml.direction.directiontype;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("string mute")
public class StringMute extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column(name = "direction_type")
    private StringMuteDirection type;

    public StringMute() {

    }

    public StringMuteDirection getType() {
        return type;
    }

    public void setType(StringMuteDirection type) {
        this.type = type;
    }
}
