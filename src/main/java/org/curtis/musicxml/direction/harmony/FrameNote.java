package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.note.notation.technical.Fingering;
import org.curtis.musicxml.note.notation.technical.Fret;
import org.curtis.musicxml.note.notation.technical.StringNumber;

public class FrameNote {
    private StringNumber string;
    private Fret fret;
    private Fingering fingering;
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
