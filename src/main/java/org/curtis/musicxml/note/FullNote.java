package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Connection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "full_note")
public class FullNote extends DatabaseItem {
    @Column
    private Boolean chord = false;
    @Transient
    // transient lilypond
    private Connection chordType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "full_note_type_id")
    private FullNoteType fullNoteType;

    public Boolean isChord() {
        return chord;
    }

    public void setChord(Boolean chord) {
        this.chord = chord;
    }

    public Connection getChordType() {
        return chordType;
    }

    public void setChordType(Connection chordType) {
        this.chordType = chordType;
    }

    public FullNoteType getFullNoteType() {
        return fullNoteType;
    }

    public void setFullNoteType(FullNoteType fullNoteType) {
        this.fullNoteType = fullNoteType;
    }
}
