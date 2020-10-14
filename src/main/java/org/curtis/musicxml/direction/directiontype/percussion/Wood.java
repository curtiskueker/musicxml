package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.WoodTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("wood")
public class Wood extends Percussion {
    @Convert(converter = WoodTypeConverter.class)
    @Column
    private WoodType value;

    public Wood() {

    }

    public WoodType getValue() {
        return value;
    }

    public void setValue(WoodType value) {
        this.value = value;
    }
}
