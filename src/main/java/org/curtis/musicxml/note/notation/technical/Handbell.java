package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("handbell")
public class Handbell extends Technical {
    @Enumerated(EnumType.STRING)
    @Column(name = "handbell_type")
    private HandbellType handbellType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Handbell() {

    }

    public HandbellType getHandbellType() {
        return handbellType;
    }

    public void setHandbellType(HandbellType handbellType) {
        this.handbellType = handbellType;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
