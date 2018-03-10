package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "group_barline")
public class GroupBarline extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "group_barline_type")
    private GroupBarlineType groupBarlineType;
    @Transient
    private String color;

    public GroupBarline() {

    }

    public GroupBarlineType getGroupBarlineType() {
        return groupBarlineType;
    }

    public void setGroupBarlineType(GroupBarlineType groupBarlineType) {
        this.groupBarlineType = groupBarlineType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
