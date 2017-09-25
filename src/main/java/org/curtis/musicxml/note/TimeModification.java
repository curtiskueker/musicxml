package org.curtis.musicxml.note;

public class TimeModification {
    private Integer actualNotes;
    private Integer normalNotes;
    private String normalType;
    // TODO: normal dot

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
}
