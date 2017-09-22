package org.curtis.musicxml.direction.type;

public class OctaveShift extends DirectionType {
    private String type;
    // TODO: number
    private Integer size;
    // TODO: dashed formatting
    // TODO: print style

    public OctaveShift() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
