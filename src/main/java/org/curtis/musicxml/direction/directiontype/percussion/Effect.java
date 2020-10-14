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
    @Column
    private EffectType value;

    public Effect() {

    }

    public EffectType getValue() {
        return value;
    }

    public void setValue(EffectType value) {
        this.value = value;
    }
}
