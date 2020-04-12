package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Font;
import org.curtis.musicxml.layout.Distance;
import org.curtis.musicxml.layout.Glyph;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.LineWidth;
import org.curtis.musicxml.layout.NoteSize;
import org.curtis.musicxml.layout.OtherAppearance;
import org.curtis.util.MathUtil;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "defaults")
public class Defaults extends DatabaseItem {
    @Column(name = "scaling_millimeters", precision = 12, scale = 4)
    private BigDecimal scalingMillimeters;
    @Column(name = "scaling_tenths", precision = 12, scale = 4)
    private BigDecimal scalingTenths;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "layout_id")
    private Layout layout;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "defaults_id", nullable = false)
    private List<LineWidth> lineWidths = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "defaults_id", nullable = false)
    private List<NoteSize> noteSizes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "defaults_id", nullable = false)
    private List<Distance> distances = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "defaults_id", nullable = false)
    private List<Glyph> glyphs = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "defaults_id", nullable = false)
    private List<OtherAppearance> otherAppearances = new ArrayList<>();
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

    public BigDecimal getScalingMillimeters() {
        return scalingMillimeters;
    }

    public void setScalingMillimeters(BigDecimal scalingMillimeters) {
        this.scalingMillimeters = scalingMillimeters;
    }

    public BigDecimal getScalingTenths() {
        return scalingTenths;
    }

    public void setScalingTenths(BigDecimal scalingTenths) {
        this.scalingTenths = scalingTenths;
    }

    public boolean hasScaling() {
        return getScalingMillimeters() != null && getScalingTenths() != null;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public boolean hasAppearance() {
        return !getLineWidths().isEmpty() || !getNoteSizes().isEmpty() || !getDistances().isEmpty() || !getGlyphs().isEmpty() || !getOtherAppearances().isEmpty();
    }

    public List<LineWidth> getLineWidths() {
        return lineWidths;
    }

    public void setLineWidths(List<LineWidth> lineWidths) {
        this.lineWidths = lineWidths;
    }

    public List<NoteSize> getNoteSizes() {
        return noteSizes;
    }

    public void setNoteSizes(List<NoteSize> noteSizes) {
        this.noteSizes = noteSizes;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    public List<Glyph> getGlyphs() {
        return glyphs;
    }

    public void setGlyphs(List<Glyph> glyphs) {
        this.glyphs = glyphs;
    }

    public List<OtherAppearance> getOtherAppearances() {
        return otherAppearances;
    }

    public void setOtherAppearances(List<OtherAppearance> otherAppearances) {
        this.otherAppearances = otherAppearances;
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

    private BigDecimal getScalingValue() {
        if(!hasScaling()) return MathUtil.ZERO;

        return MathUtil.divide(getScalingMillimeters(), getScalingTenths());
    }

    public BigDecimal getMillimeters(BigDecimal scaledValue) {
        if(scaledValue == null) return MathUtil.ZERO;
        if(!MathUtil.isPositive(getScalingValue())) return MathUtil.ZERO;

        return MathUtil.multiply(getScalingValue(), scaledValue);
    }
}
