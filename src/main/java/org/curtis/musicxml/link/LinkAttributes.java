package org.curtis.musicxml.link;

public class LinkAttributes {
    private String href;
    private String type;
    private String role;
    private String title;
    private String show = "replace";
    private String actuate = "onRequest";

    public LinkAttributes() {

    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getActuate() {
        return actuate;
    }

    public void setActuate(String actuate) {
        this.actuate = actuate;
    }
}
