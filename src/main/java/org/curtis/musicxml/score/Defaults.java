package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.layout.Appearance;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.Scaling;

import java.util.ArrayList;
import java.util.List;

public class Defaults {
    private Scaling scaling;
    private Layout layout;
    private Appearance appearance;
    private Font musicFont;
    private Font wordFont;
    private List<LyricFont> lyricFonts = new ArrayList<>();
    private List<LyricLanguage> lyricLanguages = new ArrayList<>();

    public Defaults() {

    }

    public Scaling getScaling() {
        return scaling;
    }

    public void setScaling(Scaling scaling) {
        this.scaling = scaling;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public Font getMusicFont() {
        return musicFont;
    }

    public void setMusicFont(Font musicFont) {
        this.musicFont = musicFont;
    }

    public Font getWordFont() {
        return wordFont;
    }

    public void setWordFont(Font wordFont) {
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
