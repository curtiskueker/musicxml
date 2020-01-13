package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Position;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("extend")
public class Extend extends LyricItem {
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;
    @Column
    private String color;

    public Extend() {

    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
