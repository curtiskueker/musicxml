package org.curtis.musicxml.direction.directiontype.percussion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("stick")
public class Stick extends Percussion {
    @Enumerated(EnumType.STRING)
    @Column(name = "stick_type")
    private StickType stickType;
    @Enumerated(EnumType.STRING)
    @Column(name = "stick_material")
    private StickMaterial stickMaterial;
    @Enumerated(EnumType.STRING)
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
