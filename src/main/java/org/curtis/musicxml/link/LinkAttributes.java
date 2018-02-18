package org.curtis.musicxml.link;

public class LinkAttributes {
    private String href;
    private LinkType type = LinkType.SIMPLE;
    private String role;
    private String title;
    private Show show = Show.REPLACE;
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
