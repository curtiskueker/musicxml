package org.curtis.musicxml.note;

public class TimeModification {
    private Integer actualNotes;
    private Integer normalNotes;
    private String normalType;
    private Integer normalDots;

    public TimeModification() {

    }

    public Integer getActualNotes() {
        return actualNotes;
    }

    public void setActualNotes(Integer actualNotes) {
        this.actualNotes = actualNotes;
    }

    public Integer getNormalNotes() {
        return normalNotes;
    }

    public void setNormalNotes(Integer normalNotes) {
        this.normalNotes = normalNotes;
    }

    public String getNormalType() {
        return normalType;
    }

    public void setNormalType(String normalType) {
        this.normalType = normalType;
    }

    public Integer getNormalDots() {
        return normalDots;
    }

    public void setNormalDots(Integer normalDots) {
        this.normalDots = normalDots;
    }
}
