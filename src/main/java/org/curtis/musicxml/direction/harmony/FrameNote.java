package org.curtis.musicxml.direction.harmony;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.note.notation.technical.Fingering;
import org.curtis.musicxml.note.notation.technical.Fret;
import org.curtis.musicxml.note.notation.technical.StringNumber;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "frame_note")
public class FrameNote extends OrderedItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "string_id")
    private StringNumber string;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fret_id")
    private Fret fret;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fingering_id")
    private Fingering fingering;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barre_id")
    private Barre barre;

    public FrameNote() {

    }

    public StringNumber getString() {
        return string;
    }

    public void setString(StringNumber string) {
        this.string = string;
    }

    public Fret getFret() {
        return fret;
    }

    public void setFret(Fret fret) {
        this.fret = fret;
    }

    public Fingering getFingering() {
        return fingering;
    }

    public void setFingering(Fingering fingering) {
        this.fingering = fingering;
    }

    public Barre getBarre() {
        return barre;
    }

    public void setBarre(Barre barre) {
        this.barre = barre;
    }
}
