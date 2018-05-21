package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.layout.Appearance;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.Scaling;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "defaults")
public class Defaults extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "defaults_id")
    private Scaling scaling;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "layout_id")
    private Layout layout;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appearance_id")
    private Appearance appearance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "music_font_id")
    private Font musicFont;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_font_id")
    private Font wordFont;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "defaults_id", nullable = false)
    private List<LyricFont> lyricFonts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "defaults_id", nullable = false)
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
