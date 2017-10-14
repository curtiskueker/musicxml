package org.curtis.musicxml.score;

import java.util.List;

public class PartGroup {
    private GroupName groupName;
    // TODO: group name display
    private GroupName groupAbbreviation;
    // TODO: group abbreviation display
    private GroupSymbol groupSymbol;
    private GroupBarline groupBarline;
    // TODO: group time
    // TODO: editorial
    private String type;
    private String number = "1";

    public PartGroup() {

    }

    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }

    public GroupName getGroupAbbreviation() {
        return groupAbbreviation;
    }

    public void setGroupAbbreviation(GroupName groupAbbreviation) {
        this.groupAbbreviation = groupAbbreviation;
    }

    public GroupSymbol getGroupSymbol() {
        return groupSymbol;
    }

    public void setGroupSymbol(GroupSymbol groupSymbol) {
        this.groupSymbol = groupSymbol;
    }

    public GroupBarline getGroupBarline() {
        return groupBarline;
    }

    public void setGroupBarline(GroupBarline groupBarline) {
        this.groupBarline = groupBarline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
