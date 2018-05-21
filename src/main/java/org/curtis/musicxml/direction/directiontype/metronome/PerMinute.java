package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Font;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "per_minute")
public class PerMinute extends DatabaseItem {
    @Column(name = "per_minute")
    private String perMinute;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "font_id")
    private Font font;

    public PerMinute() {

    }

    public String getPerMinute() {
        return perMinute;
    }

    public void setPerMinute(String perMinute) {
        this.perMinute = perMinute;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
