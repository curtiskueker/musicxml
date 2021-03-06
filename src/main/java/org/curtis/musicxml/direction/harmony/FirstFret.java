package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.HalignConverter;
import org.curtis.musicxml.display.Halign;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "first_fret")
public class FirstFret extends DatabaseItem {
    @Column
    private Integer value;
    @Column
    private String text;
    @Convert(converter = HalignConverter.class)
    @Column
    private Halign location;

    public FirstFret() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Halign getLocation() {
        return location;
    }

    public void setLocation(Halign location) {
        this.location = location;
    }
}
