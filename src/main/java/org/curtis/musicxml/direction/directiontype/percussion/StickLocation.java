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
    @Column
    private StickLocationType value;

    public StickLocation() {

    }

    public StickLocationType getValue() {
        return value;
    }

    public void setValue(StickLocationType value) {
        this.value = value;
    }
}
