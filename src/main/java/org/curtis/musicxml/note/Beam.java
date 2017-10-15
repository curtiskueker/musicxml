package org.curtis.musicxml.note;

public class Beam {
    private String value;
    private Integer number;
    private Boolean repeater;
    private String fan;
    private String color;

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

    public Boolean getRepeater() {
        return repeater;
    }

    public void setRepeater(Boolean repeater) {
        this.repeater = repeater;
    }

    public String getFan() {
        return fan;
    }

    public void setFan(String fan) {
        this.fan = fan;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
