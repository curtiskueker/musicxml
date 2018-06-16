package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Connection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("measure repeat")
public class MeasureRepeat extends MeasureStyle {
    @Column
    private Integer value;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Column
    private Integer slashes;

    public MeasureRepeat() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Integer getSlashes() {
        return slashes;
    }

    public void setSlashes(Integer slashes) {
        this.slashes = slashes;
    }
}
