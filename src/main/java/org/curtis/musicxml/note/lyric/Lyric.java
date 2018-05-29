package org.curtis.musicxml.note.lyric;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.notation.Tuplet;

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
    @Transient
    private Boolean endLine;
    @Transient
    private Boolean endParagraph;
    @Transient
    private Editorial editorial;
    @Column
    private String number;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    @Column
    private Location justify;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;
    @Enumerated(EnumType.STRING)
    @Column
    private Location placement;
    @Column
    private String color;
    @Transient
    private Boolean printObject;
    @Transient
    private BigDecimal totalBeats;
    @Transient
    private TimeModification timeModification;
    @Transient
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
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
