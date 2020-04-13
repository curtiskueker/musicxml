package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.common.NameDisplay;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("part group")
public class PartGroup extends PartListItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_name_id")
    private GroupName groupName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_name_display_id")
    private NameDisplay groupNameDisplay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_abbreviation_id")
    private GroupName groupAbbreviation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_abbreviation_display_id")
    private NameDisplay groupAbbreviationDisplay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_symbol_id")
    private GroupSymbol groupSymbol;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_barline_id")
    private GroupBarline groupBarline;
    @Column(name = "group_time")
    @Type(type="yes_no")
    private Boolean groupTime = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;
    @Enumerated(EnumType.STRING)
    @Column(name = "part_group_type")
    private Connection type;
    @Column(name = "part_group_number")
    private String number = "1";

    public PartGroup() {

    }

    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }

    public NameDisplay getGroupNameDisplay() {
        return groupNameDisplay;
    }

    public void setGroupNameDisplay(NameDisplay groupNameDisplay) {
        this.groupNameDisplay = groupNameDisplay;
    }

    public GroupName getGroupAbbreviation() {
        return groupAbbreviation;
    }

    public void setGroupAbbreviation(GroupName groupAbbreviation) {
        this.groupAbbreviation = groupAbbreviation;
    }

    public NameDisplay getGroupAbbreviationDisplay() {
        return groupAbbreviationDisplay;
    }

    public void setGroupAbbreviationDisplay(NameDisplay groupAbbreviationDisplay) {
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
}
