package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.NoteTypeValueConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "time_modification")
public class TimeModification extends DatabaseItem {
    @Column(name = "actual_notes")
    private Integer actualNotes;
    @Column(name = "normal_notes")
    private Integer normalNotes;
    @Convert(converter = NoteTypeValueConverter.class)
    @Column(name = "normal_type")
    private NoteTypeValue normalType;
    @Column(name = "normal_dots")
    private Integer normalDots = 0;

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
