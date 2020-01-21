package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "degree_alter")
public class DegreeAlter extends DatabaseItem {
    @Column(precision = 12, scale = 4)
    private BigDecimal semitones;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column(name = "plus_minus")
    @Type(type="yes_no")
    private Boolean plusMinus;

    public DegreeAlter() {

    }

    public BigDecimal getSemitones() {
        return semitones;
    }

    public void setSemitones(BigDecimal semitones) {
        this.semitones = semitones;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Boolean getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Boolean plusMinus) {
        this.plusMinus = plusMinus;
    }
}
