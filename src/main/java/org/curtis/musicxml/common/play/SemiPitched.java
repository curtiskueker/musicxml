package org.curtis.musicxml.common.play;

import org.curtis.musicxml.converter.SemiPitchedTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("semi pitched")
public class SemiPitched extends PlayType {
    @Convert(converter = SemiPitchedTypeConverter.class)
    @Column(name = "value")
    private SemiPitchedType semiPitchcedType;

    public SemiPitched() {

    }

    public SemiPitchedType getSemiPitchedType() {
        return semiPitchcedType;
    }

    public void setSemiPitchedType(SemiPitchedType semiPitchcedType) {
        this.semiPitchcedType = semiPitchcedType;
    }
}
