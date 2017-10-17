package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Position;

public class GroupSymbol {
    private GroupSymbolValue groupSymbolValue;
    private Position position;
    private String color;

    public GroupSymbol() {

    }

    public GroupSymbolValue getGroupSymbolValue() {
        return groupSymbolValue;
    }

    public void setGroupSymbolValue(GroupSymbolValue groupSymbolValue) {
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
