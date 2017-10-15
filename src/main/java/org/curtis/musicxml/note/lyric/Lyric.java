package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Position;

import java.util.List;

public class Lyric {
    private List<LyricItem> lyricItems;
    // TODO: end line
    // TODO: end paragraph
    // TODO: editorial
    private String number;
    private String name;
    private String justify;
    private Position position;
    private String placement;
    private String color;
    private Boolean printObject;

    public Lyric() {

    }

    public List<LyricItem> getLyricItems() {
        return lyricItems;
    }

    public void setLyricItems(List<LyricItem> lyricItems) {
        this.lyricItems = lyricItems;
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
