package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_type")
public class CreditType extends DatabaseItem {
    private String type;

    public CreditType() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
