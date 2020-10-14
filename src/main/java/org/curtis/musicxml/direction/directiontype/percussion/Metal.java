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
    @Column
    private MetalType value;

    public Metal() {

    }

    public MetalType getValue() {
        return value;
    }

    public void setValue(MetalType value) {
        this.value = value;
    }
}
