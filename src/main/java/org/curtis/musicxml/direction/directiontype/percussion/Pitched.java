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
    @Column
    private PitchedType value;
    @Column
    private String smufl;

    public Pitched() {

    }

    public PitchedType getValue() {
        return value;
    }

    public void setValue(PitchedType value) {
        this.value = value;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
