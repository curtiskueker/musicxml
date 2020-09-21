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
    @Column(name = "direction_type")
    private GlassType type;
    @Column
    private String smufl;

    public Glass() {

    }

    public GlassType getType() {
        return type;
    }

    public void setType(GlassType type) {
        this.type = type;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
