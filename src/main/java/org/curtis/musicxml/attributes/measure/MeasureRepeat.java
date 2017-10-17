package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Connection;

public class MeasureRepeat extends MeasureStyle {
    private Integer value;
    private Connection type;
    private Integer slashes;

    public MeasureRepeat() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Integer getSlashes() {
        return slashes;
    }

    public void setSlashes(Integer slashes) {
        this.slashes = slashes;
    }
}
