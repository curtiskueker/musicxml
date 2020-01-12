package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("glass")
public class Glass extends Percussion {
    @Enumerated(EnumType.STRING)
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
