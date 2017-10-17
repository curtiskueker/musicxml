package org.curtis.musicxml.direction.type.percussion;

public class Beater extends Percussion {
    private BeaterValue beaterValue;
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
