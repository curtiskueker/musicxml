package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "score")
public class Score extends DatabaseItem {
    @Transient
    private ScoreHeader scoreHeader = new ScoreHeader();
    @Transient
    private List<Part> parts = new ArrayList<>();
    @Column
    private String version = "1.0";

    public Score() {

    }

    public ScoreHeader getScoreHeader() {
        return scoreHeader;
    }

    public void setScoreHeader(ScoreHeader scoreHeader) {
        this.scoreHeader = scoreHeader;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
