package org.curtis.musicxml.barline;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bar_style_color")
public class BarStyleColor extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "bar_style")
    private BarStyle barStyle;
    @Transient
    private String color;

    public BarStyleColor() {

    }

    public BarStyle getBarStyle() {
        return barStyle;
    }

    public void setBarStyle(BarStyle barStyle) {
        this.barStyle = barStyle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
