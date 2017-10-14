package org.curtis.musicxml.direction;

import org.curtis.musicxml.score.MusicData;

public class Sound extends MusicData {
    // TODO: midi device
    // TODO: midi instrument
    // TODO: play
    private Offset offset;
    // TODO: tempo
    // TODO: dynamics
    private Boolean dacapo;
    private String segno;
    private String dalsegno;
    private String coda;
    private String tocoda;
    // TODO: divisions
    private Boolean forwardRepeat;
    private String fine;
    // TODO: time only
    private Boolean pizzicato;
    // TODO: pan
    // TODO: elevation
    // TODO: damper pedal
    // TODO: soft pedal
    // TODO: sostenuto pedal

    public Sound() {

    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public Boolean getDacapo() {
        return dacapo;
    }

    public void setDacapo(Boolean dacapo) {
        this.dacapo = dacapo;
    }

    public String getSegno() {
        return segno;
    }

    public void setSegno(String segno) {
        this.segno = segno;
    }

    public String getDalsegno() {
        return dalsegno;
    }

    public void setDalsegno(String dalsegno) {
        this.dalsegno = dalsegno;
    }

    public String getCoda() {
        return coda;
    }

    public void setCoda(String coda) {
        this.coda = coda;
    }

    public String getTocoda() {
        return tocoda;
    }

    public void setTocoda(String tocoda) {
        this.tocoda = tocoda;
    }

    public Boolean getForwardRepeat() {
        return forwardRepeat;
    }

    public void setForwardRepeat(Boolean forwardRepeat) {
        this.forwardRepeat = forwardRepeat;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public Boolean getPizzicato() {
        return pizzicato;
    }

    public void setPizzicato(Boolean pizzicato) {
        this.pizzicato = pizzicato;
    }
}
