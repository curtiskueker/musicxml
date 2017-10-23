package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Position;

public class PartSymbol {
    private GroupSymbolType groupSymbolType;
    private Integer topStaff;
    private Integer bottomStaff;
    private Position position;
    private String color;

    public PartSymbol() {

    }

    public GroupSymbolType getGroupSymbolType() {
        return groupSymbolType;
    }

    public void setGroupSymbolType(GroupSymbolType groupSymbolType) {
        this.groupSymbolType = groupSymbolType;
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
