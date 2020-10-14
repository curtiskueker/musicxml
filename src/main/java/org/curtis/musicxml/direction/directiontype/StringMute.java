package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.converter.StringMuteDirectionConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("string mute")
public class StringMute extends DirectionType {
    @Convert(converter = StringMuteDirectionConverter.class)
    @Column
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
