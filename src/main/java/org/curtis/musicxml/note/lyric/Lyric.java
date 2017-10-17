package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;

public class Lyric {
    private LyricItem lyricItem;
    private Boolean endLine;
    private Boolean endParagraph;
    private Editorial editorial;
    private String number;
    private String name;
    private Location justify;
    private Position position;
    private Location placement;
    private String color;
    private Boolean printObject;

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
}
