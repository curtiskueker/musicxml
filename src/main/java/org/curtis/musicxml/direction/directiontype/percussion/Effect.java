package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("effect")
public class Effect extends Percussion {
    @Enumerated(EnumType.STRING)
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
