package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.converter.DynamicsTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dynamics_marking")
public class DynamicsMarking extends OrderedItem {
    @Convert(converter = DynamicsTypeConverter.class)
    @Column(name = "dynamics_type")
    private DynamicsType dynamicsType;
    @Column
    private String value;
    @Column
    private String smufl;

    public DynamicsMarking() {

    }

    public DynamicsType getDynamicsType() {
        return dynamicsType;
    }

    public void setDynamicsType(DynamicsType dynamicsType) {
        this.dynamicsType = dynamicsType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
