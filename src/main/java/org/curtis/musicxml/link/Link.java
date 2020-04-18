package org.curtis.musicxml.link;

import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("link")
public class Link extends MusicData {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "link_attributes_id")
    private LinkAttributes linkAttributes;
    @Column
    private String name;
    @Column
    private String element;
    @Column
    private Integer position;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

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

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
