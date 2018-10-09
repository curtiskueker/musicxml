package org.curtis.musicxml.direction;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "offset")
public class Offset extends DatabaseItem {
    @Column(precision = 12, scale = 4)
    private BigDecimal divisions;
    @Column
    private Boolean sound;

    public Offset() {

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
