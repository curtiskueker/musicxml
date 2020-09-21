package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.EffectTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("effect")
public class Effect extends Percussion {
    @Convert(converter = EffectTypeConverter.class)
    @Column(name = "direction_type")
    private EffectType type;

    public Effect() {

    }

    public EffectType getType() {
        return type;
    }

    public void setType(EffectType type) {
        this.type = type;
    }
}
