package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "time_modification")
public class TimeModification extends DatabaseItem {
    @Column(name = "actual_notes")
    private Integer actualNotes;
    @Column(name = "normal_notes")
    private Integer normalNotes;
    @Enumerated(EnumType.STRING)
    @Column(name = "normal_type")
    private NoteTypeValue normalType;
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

    public NoteTypeValue getNormalType() {
        return normalType;
    }

    public void setNormalType(NoteTypeValue normalType) {
        this.normalType = normalType;
    }

    public Integer getNormalDots() {
        return normalDots;
    }

    public void setNormalDots(Integer normalDots) {
        this.normalDots = normalDots;
    }
}
