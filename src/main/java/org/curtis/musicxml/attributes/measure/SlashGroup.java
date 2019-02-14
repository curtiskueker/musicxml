package org.curtis.musicxml.attributes.measure;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.NoteTypeValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "slash_group")
public class SlashGroup extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "slash_type")
    private NoteTypeValue slashType;
    @Column(name = "slash_dots")
    private Integer slashDots = 0;

    public SlashGroup() {

    }

    public NoteTypeValue getSlashType() {
        return slashType;
    }

    public void setSlashType(NoteTypeValue slashType) {
        this.slashType = slashType;
    }

    public Integer getSlashDots() {
        return slashDots;
    }

    public void setSlashDots(Integer slashDots) {
        this.slashDots = slashDots;
    }
}
