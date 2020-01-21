package org.curtis.musicxml.link;

import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.score.CreditDisplay;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @ManyToOne
    @JoinColumn(name = "credit_display_id")
    private CreditDisplay creditDisplay;

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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public CreditDisplay getCreditDisplay() {
        return creditDisplay;
    }

    public void setCreditDisplay(CreditDisplay creditDisplay) {
        this.creditDisplay = creditDisplay;
    }
}
