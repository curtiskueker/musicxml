package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.StemTypeConverter;
import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stem")
public class Stem extends DatabaseItem {
    @Convert(converter = StemTypeConverter.class)
    @Column
    private StemType value;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Stem() {

    }

    public StemType getValue() {
        return value;
    }

    public void setValue(StemType value) {
        this.value = value;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
