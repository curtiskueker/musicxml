package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Font;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tuplet_number")
public class TupletNumber extends DatabaseItem {
    @Column
    private Integer value;
    @Transient
    private Font font;
    @Transient
    private String color;

    public TupletNumber() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
