package org.curtis.musicxml.score;

import org.curtis.musicxml.link.Link;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("link")
public class CreditLink extends CreditDisplay {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "link_id")
    private Link link;

    public CreditLink() {

    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
