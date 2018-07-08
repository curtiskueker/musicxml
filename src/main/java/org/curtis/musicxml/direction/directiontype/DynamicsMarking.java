package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "dynamics_marking")
public class DynamicsMarking extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "dynamics_type")
    private DynamicsType dynamicsType;

    public DynamicsMarking() {

    }

    public DynamicsType getDynamicsType() {
        return dynamicsType;
    }

    public void setDynamicsType(DynamicsType dynamicsType) {
        this.dynamicsType = dynamicsType;
    }
}
