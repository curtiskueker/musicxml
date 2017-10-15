package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Position;

public class GroupSymbol {
    private String groupSymbolValue;
    private Position position;
    private String color;

    public GroupSymbol() {

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
