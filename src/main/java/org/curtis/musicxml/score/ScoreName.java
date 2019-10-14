package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "score", uniqueConstraints = @UniqueConstraint(columnNames = "score_name"))
public class ScoreName extends DatabaseItem {
    @Column(name = "score_name")
    private String scoreName;

    public ScoreName() {

    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }
}
