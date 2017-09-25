package org.curtis.musicxml.note.lyric;

import java.util.List;

public class Lyric {
    private List<LyricItem> lyricItems;
    // TODO: end line
    // TODO: end paragraph
    // TODO: editorial
    private String number;
    private String name;
    // TODO: justify
    // TODO: position
    // TODO: placement
    // TODO: color
    // TODO: print object

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
}
