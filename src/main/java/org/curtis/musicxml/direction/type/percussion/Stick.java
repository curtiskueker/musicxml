package org.curtis.musicxml.direction.type.percussion;

public class Stick extends Percussion {
    private String stickType;
    private String stickMaterial;
    private String tip;

    public Stick() {

    }

    public String getStickType() {
        return stickType;
    }

    public void setStickType(String stickType) {
        this.stickType = stickType;
    }

    public String getStickMaterial() {
        return stickMaterial;
    }

    public void setStickMaterial(String stickMaterial) {
        this.stickMaterial = stickMaterial;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
