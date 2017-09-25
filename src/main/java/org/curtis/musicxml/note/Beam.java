package org.curtis.musicxml.note;

public class Beam {
    private String value;
    private Integer number;
    // TODO: repeater
    private String fan;
    // TODO: color

    public Beam() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFan() {
        return fan;
    }

    public void setFan(String fan) {
        this.fan = fan;
    }
}
