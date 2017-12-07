package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.note.NoteTypeValue;

public class SlashGroup {
    private NoteTypeValue slashType;
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
