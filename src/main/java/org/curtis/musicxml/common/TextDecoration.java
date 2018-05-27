package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "text_decoration")
public class TextDecoration extends DatabaseItem {
    @Column
    private Integer underline;
    @Column
    private Integer overline;
    @Column(name = "line_through")
    private Integer lineThrough;

    public TextDecoration() {

    }

    public Integer getUnderline() {
        return underline;
    }

    public void setUnderline(Integer underline) {
        this.underline = underline;
    }

    public Integer getOverline() {
        return overline;
    }

    public void setOverline(Integer overline) {
        this.overline = overline;
    }

    public Integer getLineThrough() {
        return lineThrough;
    }

    public void setLineThrough(Integer lineThrough) {
        this.lineThrough = lineThrough;
    }
}
