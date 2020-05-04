package org.curtis.musicxml.identity;

import org.curtis.database.OrderedItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "identification_type")
public class IdentificationType extends OrderedItem {
    @Column(name = "id_name")
    private String idName;
    @Column(name = "id_value")
    private String idValue;
    @Column(name = "id_type")
    private String idType;

    public IdentificationType() {

    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }
}
