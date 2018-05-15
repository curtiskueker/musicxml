package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Connection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("extend")
public class Extend extends LyricItem {
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Transient
    private PrintStyle printStyle;

    public Extend() {

    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
