package org.curtis.database;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class OrderedElement extends OrderedItem {
    @Column(name = "element_id")
    private String elementId;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
