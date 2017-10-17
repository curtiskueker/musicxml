package org.curtis.musicxml.direction.type.percussion;

public class Effect extends Percussion {
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
