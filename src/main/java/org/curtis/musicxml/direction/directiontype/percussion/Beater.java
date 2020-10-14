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
    @Column
    private BeaterValue value;
    @Convert(converter = TipDirectionConverter.class)
    @Column
    private TipDirection tip;

    public Beater() {

    }

    public BeaterValue getValue() {
        return value;
    }

    public void setValue(BeaterValue value) {
        this.value = value;
    }

    public TipDirection getTip() {
        return tip;
    }

    public void setTip(TipDirection tip) {
        this.tip = tip;
    }
}
