package org.curtis.database;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class OrderedItem extends DatabaseItem {
    @Column
    private Integer ordering;

    public OrderedItem() {

    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}
