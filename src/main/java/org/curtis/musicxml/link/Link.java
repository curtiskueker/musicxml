package org.curtis.musicxml.link;

import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("link")
public class Link extends MusicData {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "link_attributes_id")
    private LinkAttributes linkAttributes;
    @Column
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "element_position_id")
    private ElementPosition elementPosition;
    @Transient
    private Position position;

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
