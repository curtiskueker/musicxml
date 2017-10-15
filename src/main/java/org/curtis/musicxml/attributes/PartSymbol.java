package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.Position;

public class PartSymbol {
    private String groupSymbolValue;
    // TODO: top staff
    // TODO: bottom staff
    private Position position;
    private String color;

    public PartSymbol() {

    }

    public String getGroupSymbolValue() {
        return groupSymbolValue;
    }

    public void setGroupSymbolValue(String groupSymbolValue) {
        this.groupSymbolValue = groupSymbolValue;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
