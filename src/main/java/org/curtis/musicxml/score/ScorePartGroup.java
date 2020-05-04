package org.curtis.musicxml.score;

import org.curtis.database.OrderedItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "score_part_group")
public class ScorePartGroup extends OrderedItem {
    @Column(name = "group_name")
    private String group;

    public ScorePartGroup() {

    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
