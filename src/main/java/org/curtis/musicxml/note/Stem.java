package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "stem")
public class Stem extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private StemType type;
    @Transient
    private YPosition yPosition;
    @Transient
    private String color;

    public Stem() {

    }

    public StemType getType() {
        return type;
    }

    public void setType(StemType type) {
        this.type = type;
    }

    public YPosition getyPosition() {
        return yPosition;
    }

    public void setyPosition(YPosition yPosition) {
        this.yPosition = yPosition;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
