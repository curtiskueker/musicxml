package org.curtis.musicxml.link;

import org.curtis.musicxml.score.MusicData;

public class Link extends MusicData {
    private LinkAttributes linkAttributes;
    private String name;
    private ElementPosition elementPosition;
    // TODO: position

    public Link() {

    }

    public LinkAttributes getLinkAttributes() {
        return linkAttributes;
    }

    public void setLinkAttributes(LinkAttributes linkAttributes) {
        this.linkAttributes = linkAttributes;
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
