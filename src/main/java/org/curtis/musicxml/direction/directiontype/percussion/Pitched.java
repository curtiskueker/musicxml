package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.PitchedTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("pitched")
public class Pitched extends Percussion {
    @Convert(converter = PitchedTypeConverter.class)
    @Column(name = "direction_type")
    private PitchedType type;
    @Column
    private String smufl;

    public Pitched() {

    }

    public PitchedType getType() {
        return type;
    }

    public void setType(PitchedType type) {
        this.type = type;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
