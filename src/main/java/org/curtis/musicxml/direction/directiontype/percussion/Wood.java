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
    @Column(name = "direction_type")
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
