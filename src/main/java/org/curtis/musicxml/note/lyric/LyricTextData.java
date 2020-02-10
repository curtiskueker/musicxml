package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.display.FormattedDisplay;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lyric_text_data")
public class LyricTextData extends FormattedDisplay {
    public LyricTextData() {

    }
}
