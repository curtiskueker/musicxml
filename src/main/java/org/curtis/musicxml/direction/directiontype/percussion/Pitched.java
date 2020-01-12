package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("pitched")
public class Pitched extends Percussion {
    @Enumerated(EnumType.STRING)
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
