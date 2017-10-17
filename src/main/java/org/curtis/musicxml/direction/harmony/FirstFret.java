package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.Location;

public class FirstFret {
    private Integer value;
    private String text;
    private Location location;

    public FirstFret() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
