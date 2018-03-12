package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("wood")
public class Wood extends Percussion {
    @Enumerated(EnumType.STRING)
    @Column
    private WoodType type;

    public Wood() {

    }

    public WoodType getType() {
        return type;
    }

    public void setType(WoodType type) {
        this.type = type;
    }
}
