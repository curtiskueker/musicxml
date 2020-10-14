package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.converter.ConnectionConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("dashes")
public class Dashes extends DirectionType {
    @Convert(converter = ConnectionConverter.class)
    @Column
    private Connection type;
    @Column(name = "direction_type_number")
    private Integer number;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashed_formatting_id")
    private DashedFormatting dashedFormatting;

    public Dashes() {

    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public DashedFormatting getDashedFormatting() {
        return dashedFormatting;
    }

    public void setDashedFormatting(DashedFormatting dashedFormatting) {
        this.dashedFormatting = dashedFormatting;
    }
}
