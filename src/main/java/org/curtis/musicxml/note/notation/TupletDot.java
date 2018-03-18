package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Font;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tuplet_dot")
public class TupletDot extends DatabaseItem {
    @Transient
    private Font font;
    @Transient
    private String color;

    public TupletDot() {

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
