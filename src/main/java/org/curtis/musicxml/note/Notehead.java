package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Font;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notehead")
public class Notehead extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private NoteheadType type;
    @Column
    @Type(type="yes_no")
    private Boolean filled;
    @Column
    @Type(type="yes_no")
    private Boolean parentheses;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "font_id")
    private Font font;
    @Column
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
