package org.curtis.musicxml.attributes.measure;

public class MeasureRepeat extends MeasureStyle {
    private Integer value;
    private String type;
    private Integer slashes;

    public MeasureRepeat() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSlashes() {
        return slashes;
    }

    public void setSlashes(Integer slashes) {
        this.slashes = slashes;
    }
}
