package org.curtis.musicxml.link;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "link_attributes")
public class LinkAttributes extends DatabaseItem {
    @Column
    private String href;
    @Enumerated(EnumType.STRING)
    @Column
    private LinkType type = LinkType.SIMPLE;
    @Column(name = "link_role")
    private String role;
    @Column
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "link_show")
    private Show show = Show.REPLACE;
    @Enumerated(EnumType.STRING)
    @Column
    private Actuate actuate = Actuate.ON_REQUEST;

    public LinkAttributes() {

    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public LinkType getType() {
        return type;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Actuate getActuate() {
        return actuate;
    }

    public void setActuate(Actuate actuate) {
        this.actuate = actuate;
    }
}
