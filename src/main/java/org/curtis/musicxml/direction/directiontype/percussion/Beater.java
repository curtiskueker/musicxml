package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("beater")
public class Beater extends Percussion {
    @Enumerated(EnumType.STRING)
    @Column(name = "direction_type")
    private BeaterValue beaterValue;
    @Enumerated(EnumType.STRING)
    @Column
    private TipDirection tip;

    public Beater() {

    }

    public BeaterValue getBeaterValue() {
        return beaterValue;
    }

    public void setBeaterValue(BeaterValue beaterValue) {
        this.beaterValue = beaterValue;
    }

    public TipDirection getTip() {
        return tip;
    }

    public void setTip(TipDirection tip) {
        this.tip = tip;
    }
}
