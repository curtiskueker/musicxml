package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.DashedFormatting;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("octave shift")
public class OctaveShift extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column(name = "direction_type")
    private OctaveShiftType type;
    @Column(name = "direction_type_number")
    private Integer number;
    @Column(name = "direction_type_size")
    private Integer size = 8;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashed_formatting_id")
    private DashedFormatting dashedFormatting;

    public OctaveShift() {

    }

    public OctaveShiftType getType() {
        return type;
    }

    public void setType(OctaveShiftType type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public DashedFormatting getDashedFormatting() {
        return dashedFormatting;
    }

    public void setDashedFormatting(DashedFormatting dashedFormatting) {
        this.dashedFormatting = dashedFormatting;
    }
}
