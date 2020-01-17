package org.curtis.musicxml.note.lyric;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Font;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "elision")
public class Elision extends DatabaseItem {
    @Column
    private String value;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "font_id")
    private Font font;
    @Column
    private String color;
    @Column
    private String smufl;

    public Elision() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
