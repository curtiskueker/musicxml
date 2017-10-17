package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.Connection;

public class Barre {
    private Connection type;
    private String color;

    public Barre() {

    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
