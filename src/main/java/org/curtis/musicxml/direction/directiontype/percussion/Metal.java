package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("metal")
public class Metal extends Percussion {
    @Enumerated(EnumType.STRING)
    @Column(name = "direction_type")
    private MetalType type;

    public Metal() {

    }

    public MetalType getType() {
        return type;
    }

    public void setType(MetalType type) {
        this.type = type;
    }
}
