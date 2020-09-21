package org.curtis.musicxml.direction.directiontype.percussion;

import org.curtis.musicxml.converter.StickMaterialConverter;
import org.curtis.musicxml.converter.StickTypeConverter;
import org.curtis.musicxml.converter.TipDirectionConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("stick")
public class Stick extends Percussion {
    @Convert(converter = StickTypeConverter.class)
    @Column(name = "stick_type")
    private StickType stickType;
    @Convert(converter = StickMaterialConverter.class)
    @Column(name = "stick_material")
    private StickMaterial stickMaterial;
    @Convert(converter = TipDirectionConverter.class)
    @Column
    private TipDirection tip;

    public Stick() {

    }

    public StickType getStickType() {
        return stickType;
    }

    public void setStickType(StickType stickType) {
        this.stickType = stickType;
    }

    public StickMaterial getStickMaterial() {
        return stickMaterial;
    }

    public void setStickMaterial(StickMaterial stickMaterial) {
        this.stickMaterial = stickMaterial;
    }

    public TipDirection getTip() {
        return tip;
    }

    public void setTip(TipDirection tip) {
        this.tip = tip;
    }
}
