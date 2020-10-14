package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.GlassTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("glass")
public class Glass extends Percussion {
    @Convert(converter = GlassTypeConverter.class)
    @Column
    private GlassType value;
    @Column
    private String smufl;

    public Glass() {

    }

    public GlassType getValue() {
        return value;
    }

    public void setValue(GlassType value) {
        this.value = value;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
