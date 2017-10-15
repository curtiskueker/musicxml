package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.Position;

public class PartSymbol {
    private String groupSymbolValue;
    private Integer topStaff;
    private Integer bottomStaff;
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

    public Integer getTopStaff() {
        return topStaff;
    }

    public void setTopStaff(Integer topStaff) {
        this.topStaff = topStaff;
    }

    public Integer getBottomStaff() {
        return bottomStaff;
    }

    public void setBottomStaff(Integer bottomStaff) {
        this.bottomStaff = bottomStaff;
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
