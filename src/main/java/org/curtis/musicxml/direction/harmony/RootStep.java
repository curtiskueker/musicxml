package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.StepConverter;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.Step;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "root_step")
public class RootStep extends DatabaseItem {
    @Convert(converter = StepConverter.class)
    @Column
    private Step value;
    @Column
    private String text;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public RootStep() {

    }

    public Step getValue() {
        return value;
    }

    public void setValue(Step value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
