package org.curtis.musicxml.score;

public class GroupBarline {
    private GroupBarlineType groupBarlineValue;
    private String color;

    public GroupBarline() {

    }

    public GroupBarlineType getGroupBarlineValue() {
        return groupBarlineValue;
    }

    public void setGroupBarlineValue(GroupBarlineType groupBarlineValue) {
        this.groupBarlineValue = groupBarlineValue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
