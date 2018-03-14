package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Font;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Notehead extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private NoteheadType type;
    @Transient
    private Boolean filled;
    @Transient
    private Boolean parentheses;
    @Transient
    private Font font;
    @Transient
    private String color;

    public Notehead() {

    }

    public NoteheadType getType() {
        return type;
    }

    public void setType(NoteheadType type) {
        this.type = type;
    }

    public Boolean getFilled() {
        return filled;
    }

    public void setFilled(Boolean filled) {
        this.filled = filled;
    }

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
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
