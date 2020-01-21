package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group_barline")
public class GroupBarline extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "group_barline_type")
    private GroupBarlineType groupBarlineType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public GroupBarline() {

    }

    public GroupBarlineType getGroupBarlineType() {
        return groupBarlineType;
    }

    public void setGroupBarlineType(GroupBarlineType groupBarlineType) {
        this.groupBarlineType = groupBarlineType;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
