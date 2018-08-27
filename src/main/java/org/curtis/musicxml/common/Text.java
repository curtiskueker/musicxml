package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.NoteheadText;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "text_display")
@DiscriminatorColumn(name = "text_type")
public abstract class Text extends DatabaseItem {
    @ManyToOne
    @JoinColumn(name = "name_display_id")
    private NameDisplay nameDisplay;
    @ManyToOne
    @JoinColumn(name = "notehead_text_id")
    private NoteheadText noteheadText;

    public NameDisplay getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(NameDisplay nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public NoteheadText getNoteheadText() {
        return noteheadText;
    }

    public void setNoteheadText(NoteheadText noteheadText) {
        this.noteheadText = noteheadText;
    }
}
