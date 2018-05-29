package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stem")
public class Stem extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private StemType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "y_position_id")
    private YPosition yPosition;
    @Column
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
