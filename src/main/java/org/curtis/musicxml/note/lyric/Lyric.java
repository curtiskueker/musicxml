package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.Position;

public class Lyric {
    private LyricItem lyricItem;
    private Boolean endLine;
    private Boolean endParagraph;
    private Editorial editorial;
    private String number;
    private String name;
    private String justify;
    private Position position;
    private String placement;
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

    public String getJustify() {
        return justify;
    }

    public void setJustify(String justify) {
        this.justify = justify;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
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
