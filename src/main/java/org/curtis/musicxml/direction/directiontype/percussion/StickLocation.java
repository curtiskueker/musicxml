package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("stick location")
public class StickLocation extends Percussion {
    @Enumerated(EnumType.STRING)
    @Column
    private StickLocationType type;

    public StickLocation() {

    }

    public StickLocationType getType() {
        return type;
    }

    public void setType(StickLocationType type) {
        this.type = type;
    }
}
