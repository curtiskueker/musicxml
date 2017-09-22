package org.curtis.musicxml.direction.type.percussion;

public class Beater extends Percussion {
    private String beaterValue;
    private String tip;

    public Beater() {

    }

    public String getBeaterValue() {
        return beaterValue;
    }

    public void setBeaterValue(String beaterValue) {
        this.beaterValue = beaterValue;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
