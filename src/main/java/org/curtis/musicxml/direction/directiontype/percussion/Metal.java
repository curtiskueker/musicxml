package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.MetalTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("metal")
public class Metal extends Percussion {
    @Convert(converter = MetalTypeConverter.class)
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
