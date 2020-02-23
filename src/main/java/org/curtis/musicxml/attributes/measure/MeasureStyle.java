package org.curtis.musicxml.attributes.measure;

import org.curtis.database.DatabaseElement;
import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "measure_style")
@DiscriminatorColumn(name = "measure_style_type")
public abstract class MeasureStyle extends DatabaseElement {
    @Column(name = "measure_style_number")
    private Integer number;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
