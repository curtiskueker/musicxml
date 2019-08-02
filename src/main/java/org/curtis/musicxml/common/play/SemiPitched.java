package org.curtis.musicxml.common.play;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("semi pitched")
public class SemiPitched extends PlayType {
    @Enumerated(EnumType.STRING)
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
