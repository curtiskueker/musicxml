package org.curtis.musicxml.score;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private ScoreHeader scoreHeader = new ScoreHeader();
    private List<Part> parts = new ArrayList<>();
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
