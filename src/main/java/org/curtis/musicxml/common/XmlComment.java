package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "xml_comment")
public class XmlComment extends DatabaseItem {
    @Column
    private String target;
    @Column
    private String data;
    @Column
    private String parent;
    @Column(name = "next_sibling")
    private String nextSibling;

    public XmlComment() {

    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(String nextSibling) {
        this.nextSibling = nextSibling;
    }
}
