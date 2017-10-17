package org.curtis.musicxml.direction.type.percussion;

public class Stick extends Percussion {
    private StickType stickType;
    private StickMaterial stickMaterial;
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
