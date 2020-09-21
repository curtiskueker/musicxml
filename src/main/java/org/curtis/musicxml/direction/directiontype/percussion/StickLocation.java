package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.StickLocationTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("stick location")
public class StickLocation extends Percussion {
    @Convert(converter = StickLocationTypeConverter.class)
    @Column(name = "direction_type")
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
