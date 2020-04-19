package org.curtis.musicxml.note.notation.technical;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("harmon mute")
public class HarmonMute extends Technical {
    @Enumerated(EnumType.STRING)
    @Column
    private HarmonClosedValue value;
    @Enumerated(EnumType.STRING)
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
