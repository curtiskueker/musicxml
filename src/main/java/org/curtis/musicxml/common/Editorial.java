package org.curtis.musicxml.common;

public class Editorial {
    private FormattedText footnote;
    private Level level;

    public Editorial() {

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
}
