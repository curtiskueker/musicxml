package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Connection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("measure repeat")
public class MeasureRepeat extends MeasureStyle {
    @Transient
    private Integer value;
    @Transient
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
