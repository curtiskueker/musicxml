package org.curtis.musicxml.attributes.measure;

public class MeasureRepeat extends MeasureStyle {
    // TODO: value
    private String type;
    private Integer slashes;

    public MeasureRepeat() {

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
