package org.curtis.musicxml.direction;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Level;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "editorial_voice_direction")
public class EditorialVoiceDirection extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "footnote_id")
    private FormattedText footnote;
    @Transient
    private Level level;
    @Transient
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
