package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.converter.StaffDivideSymbolConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("staff divide")
public class StaffDivide extends DirectionType {
    @Convert(converter = StaffDivideSymbolConverter.class)
    @Column
    private StaffDivideSymbol type;

    public StaffDivide() {

    }

    public StaffDivideSymbol getType() {
        return type;
    }

    public void setType(StaffDivideSymbol type) {
        this.type = type;
    }
}
