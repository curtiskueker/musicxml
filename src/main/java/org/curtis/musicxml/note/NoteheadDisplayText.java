package org.curtis.musicxml.note;

import org.curtis.musicxml.common.FormattedText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("notehead_display_text")
public class NoteheadDisplayText extends NoteheadText {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_id")
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
