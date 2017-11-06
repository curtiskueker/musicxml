package org.curtis.musicxml.note;

public class Beam {
    private BeamType type;
    private Integer number = 1;
    private Boolean repeater;
    private BeamFan fan;
    private String color;

    public Beam() {

    }

    public BeamType getType() {
        return type;
    }

    public void setType(BeamType type) {
        this.type = type;
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

    public BeamFan getFan() {
        return fan;
    }

    public void setFan(BeamFan fan) {
        this.fan = fan;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
