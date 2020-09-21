package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.converter.HarmonClosedLocationConverter;
import org.curtis.musicxml.converter.HarmonClosedValueConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("harmon mute")
public class HarmonMute extends Technical {
    @Convert(converter = HarmonClosedValueConverter.class)
    @Column
    private HarmonClosedValue value;
    @Convert(converter = HarmonClosedLocationConverter.class)
    @Column
    private HarmonClosedLocation location;

    public HarmonMute() {

    }

    public HarmonClosedValue getValue() {
        return value;
    }

    public void setValue(HarmonClosedValue value) {
        this.value = value;
    }

    public HarmonClosedLocation getLocation() {
        return location;
    }

    public void setLocation(HarmonClosedLocation location) {
        this.location = location;
    }
}
