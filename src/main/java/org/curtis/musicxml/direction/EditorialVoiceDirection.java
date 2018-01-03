package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Level;

public class EditorialVoiceDirection {
    private FormattedText footnote;
    private Level level;
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
