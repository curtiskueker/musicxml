package org.curtis.musicxml.score;

import java.util.List;

public class Defaults {
    // TODO: scaling
    // TODO: appearance
    private BasicFont musicFont;
    private BasicFont wordFont;
    private List<LyricFont> lyricFonts;
    private List<LyricLanguage> lyricLanguages;

    public Defaults() {

    }

    public BasicFont getMusicFont() {
        return musicFont;
    }

    public void setMusicFont(BasicFont musicFont) {
        this.musicFont = musicFont;
    }

    public BasicFont getWordFont() {
        return wordFont;
    }

    public void setWordFont(BasicFont wordFont) {
        this.wordFont = wordFont;
    }

    public List<LyricFont> getLyricFonts() {
        return lyricFonts;
    }

    public void setLyricFonts(List<LyricFont> lyricFonts) {
        this.lyricFonts = lyricFonts;
    }

    public List<LyricLanguage> getLyricLanguages() {
        return lyricLanguages;
    }

    public void setLyricLanguages(List<LyricLanguage> lyricLanguages) {
        this.lyricLanguages = lyricLanguages;
    }
}
