package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.Connection;

import java.util.ArrayList;
import java.util.List;

public class PartGroup {
    private GroupName groupName;
    private PartName groupNameDisplay;
    private GroupName groupAbbreviation;
    private PartName groupAbbreviationDisplay;
    private GroupSymbol groupSymbol;
    private GroupBarline groupBarline;
    private Boolean groupTime;
    private Editorial editorial;
    private Connection type;
    private String number = "1";
    private List<ScorePart> scoreParts = new ArrayList<>();

    public PartGroup() {

    }

    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }

    public PartName getGroupNameDisplay() {
        return groupNameDisplay;
    }

    public void setGroupNameDisplay(PartName groupNameDisplay) {
        this.groupNameDisplay = groupNameDisplay;
    }

    public GroupName getGroupAbbreviation() {
        return groupAbbreviation;
    }

    public void setGroupAbbreviation(GroupName groupAbbreviation) {
        this.groupAbbreviation = groupAbbreviation;
    }

    public PartName getGroupAbbreviationDisplay() {
        return groupAbbreviationDisplay;
    }

    public void setGroupAbbreviationDisplay(PartName groupAbbreviationDisplay) {
        this.groupAbbreviationDisplay = groupAbbreviationDisplay;
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

    public Boolean getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Boolean groupTime) {
        this.groupTime = groupTime;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<ScorePart> getScoreParts() {
        return scoreParts;
    }

    public void setScoreParts(List<ScorePart> scoreParts) {
        this.scoreParts = scoreParts;
    }
}
