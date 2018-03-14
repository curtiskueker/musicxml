package org.curtis.musicxml.note;

import org.curtis.musicxml.common.FormattedText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("notehead_display_text")
public class NoteheadDisplayText extends NoteheadText {
    @Transient
    private FormattedText text;

    public NoteheadDisplayText() {

    }

    public FormattedText getText() {
        return text;
    }

    public void setText(FormattedText text) {
        this.text = text;
    }
}
