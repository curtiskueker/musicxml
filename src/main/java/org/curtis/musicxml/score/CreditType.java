package org.curtis.musicxml.score;

import org.curtis.database.OrderedItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_type")
public class CreditType extends OrderedItem {
    @Column
    private String value;

    public CreditType() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
