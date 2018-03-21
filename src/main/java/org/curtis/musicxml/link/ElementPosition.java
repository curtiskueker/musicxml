package org.curtis.musicxml.link;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "element_position")
public class ElementPosition extends DatabaseItem {
    @Column
    private String element;
    @Column
    private Integer position;

    public ElementPosition() {

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
}
