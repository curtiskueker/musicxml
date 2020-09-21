package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.BeaterValueConverter;
import org.curtis.musicxml.converter.TipDirectionConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("beater")
public class Beater extends Percussion {
    @Convert(converter = BeaterValueConverter.class)
    @Column(name = "direction_type")
    private BeaterValue beaterValue;
    @Convert(converter = TipDirectionConverter.class)
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
