package org.curtis.musicxml.direction;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "direction_offset")
public class DirectionOffset extends DatabaseItem {
    @Column(precision = 12, scale = 4)
    private BigDecimal divisions;
    @Column
    @Type(type="yes_no")
    private Boolean sound;

    public DirectionOffset() {

    }

    public BigDecimal getDivisions() {
        return divisions;
    }

    public void setDivisions(BigDecimal divisions) {
        this.divisions = divisions;
    }

    public Boolean getSound() {
        return sound;
    }

    public void setSound(Boolean sound) {
        this.sound = sound;
    }
}
