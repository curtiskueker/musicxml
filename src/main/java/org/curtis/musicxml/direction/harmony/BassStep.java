package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.note.Step;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bass_step")
public class BassStep extends DatabaseItem {
    @Transient
    private Step step;
    @Column
    private String text;
    @Transient
    private PrintStyle printStyle;

    public BassStep() {

    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
