package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.NoteheadTypeConverter;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notehead")
public class Notehead extends DatabaseItem {
    @Convert(converter = NoteheadTypeConverter.class)
    @Column
    private NoteheadType value;
    @Column
    @Type(type="yes_no")
    private Boolean filled;
    @Column
    @Type(type="yes_no")
    private Boolean parentheses;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column
    private String smufl;

    public Notehead() {

    }

    public NoteheadType getValue() {
        return value;
    }

    public void setValue(NoteheadType value) {
        this.value = value;
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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
