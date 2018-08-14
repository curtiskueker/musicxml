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
    private SemiPitchcedType semiPitchcedType;

    public SemiPitched() {

    }

    public SemiPitchcedType getSemiPitchcedType() {
        return semiPitchcedType;
    }

    public void setSemiPitchcedType(SemiPitchcedType semiPitchcedType) {
        this.semiPitchcedType = semiPitchcedType;
    }
}
