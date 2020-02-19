package org.curtis.musicxml.note.notation.technical;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("handbell")
public class Handbell extends Technical {
    @Enumerated(EnumType.STRING)
    @Column(name = "handbell_type")
    private HandbellType handbellType;

    public Handbell() {

    }

    public HandbellType getHandbellType() {
        return handbellType;
    }

    public void setHandbellType(HandbellType handbellType) {
        this.handbellType = handbellType;
    }
}
