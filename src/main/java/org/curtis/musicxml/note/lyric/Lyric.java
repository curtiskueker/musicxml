package org.curtis.musicxml.note.lyric;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.notation.Tuplet;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "lyric")
public class Lyric extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lyric_item_id")
    private LyricItem lyricItem;
    @Column(name = "end_line")
    @Type(type="yes_no")
    private Boolean endLine = false;
    @Column(name = "end_paragraph")
    @Type(type="yes_no")
    private Boolean endParagraph = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;
    @Column(name = "lyric_number")
    private String number;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    @Column
    private Location justify;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;
    @Column(name = "time_only")
    private String timeOnly;
    @Transient
    // used by lilypond
    private BigDecimal totalBeats;
    @Transient
    // used by lilypond
    private TimeModification timeModification;
    @Transient
    // used by lilypond
    private Tuplet tuplet;

    public Lyric() {

    }

    public LyricItem getLyricItem() {
        return lyricItem;
    }

    public void setLyricItem(LyricItem lyricItem) {
        this.lyricItem = lyricItem;
    }

    public Boolean getEndLine() {
        return endLine;
    }

    public void setEndLine(Boolean endLine) {
        this.endLine = endLine;
    }

    public Boolean getEndParagraph() {
        return endParagraph;
    }

    public void setEndParagraph(Boolean endParagraph) {
        this.endParagraph = endParagraph;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getJustify() {
        return justify;
    }

    public void setJustify(Location justify) {
        this.justify = justify;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }

    public String getTimeOnly() {
        return timeOnly;
    }

    public void setTimeOnly(String timeOnly) {
        this.timeOnly = timeOnly;
    }

    public BigDecimal getTotalBeats() {
        return totalBeats;
    }

    public void setTotalBeats(BigDecimal totalBeats) {
        this.totalBeats = totalBeats;
    }

    public TimeModification getTimeModification() {
        return timeModification;
    }

    public void setTimeModification(TimeModification timeModification) {
        this.timeModification = timeModification;
    }

    public Tuplet getTuplet() {
        return tuplet;
    }

    public void setTuplet(Tuplet tuplet) {
        this.tuplet = tuplet;
    }
}
