package org.curtis.musicxml.direction;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Level;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "editorial_voice_direction")
public class EditorialVoiceDirection extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "footnote_id")
    private FormattedText footnote;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Level level;
    @Column
    private String voice;

    public EditorialVoiceDirection() {

    }

    public FormattedText getFootnote() {
        return footnote;
    }

    public void setFootnote(FormattedText footnote) {
        this.footnote = footnote;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
