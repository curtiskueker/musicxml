package org.curtis.musicxml.link;

import org.curtis.musicxml.score.MusicData;

public class Bookmark extends MusicData {
    private String id;
    private String name;
    private ElementPosition elementPosition;

    public Bookmark() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementPosition getElementPosition() {
        return elementPosition;
    }

    public void setElementPosition(ElementPosition elementPosition) {
        this.elementPosition = elementPosition;
    }
}
