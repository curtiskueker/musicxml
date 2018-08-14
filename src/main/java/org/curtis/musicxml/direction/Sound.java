package org.curtis.musicxml.direction;

import org.curtis.musicxml.score.MusicData;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("sound")
public class Sound extends MusicData {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sound_id", nullable = false)
    private List<SoundMidi> soundMidis = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offset_id")
    private Offset offset;
    @Column
    private BigDecimal tempo;
    @Column
    private BigDecimal dynamics;
    @Column
    private Boolean dacapo;
    @Column
    private String segno;
    @Column
    private String dalsegno;
    @Column
    private String coda;
    @Column
    private String tocoda;
    @Column
    private BigDecimal divisions;
    @Column(name = "forward_repeat")
    private Boolean forwardRepeat;
    @Column
    private String fine;
    @Column(name = "time_only")
    private String timeOnly;
    @Column
    private Boolean pizzicato;
    @Column
    private BigDecimal pan;
    @Column
    private BigDecimal elevation;
    @Column(name = "damper_pedal")
    private String damperPedal;
    @Column(name = "soft_pedal")
    private String softPedal;
    @Column(name = "sostenuto_pedal")
    private String sostenutoPedal;

    public Sound() {

    }

    public List<SoundMidi> getSoundMidis() {
        return soundMidis;
    }

    public void setSoundMidis(List<SoundMidi> soundMidis) {
        this.soundMidis = soundMidis;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public BigDecimal getTempo() {
        return tempo;
    }

    public void setTempo(BigDecimal tempo) {
        this.tempo = tempo;
    }

    public BigDecimal getDynamics() {
        return dynamics;
    }

    public void setDynamics(BigDecimal dynamics) {
        this.dynamics = dynamics;
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

    public BigDecimal getDivisions() {
        return divisions;
    }

    public void setDivisions(BigDecimal divisions) {
        this.divisions = divisions;
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

    public String getTimeOnly() {
        return timeOnly;
    }

    public void setTimeOnly(String timeOnly) {
        this.timeOnly = timeOnly;
    }

    public Boolean getPizzicato() {
        return pizzicato;
    }

    public void setPizzicato(Boolean pizzicato) {
        this.pizzicato = pizzicato;
    }

    public BigDecimal getPan() {
        return pan;
    }

    public void setPan(BigDecimal pan) {
        this.pan = pan;
    }

    public BigDecimal getElevation() {
        return elevation;
    }

    public void setElevation(BigDecimal elevation) {
        this.elevation = elevation;
    }

    public String getDamperPedal() {
        return damperPedal;
    }

    public void setDamperPedal(String damperPedal) {
        this.damperPedal = damperPedal;
    }

    public String getSoftPedal() {
        return softPedal;
    }

    public void setSoftPedal(String softPedal) {
        this.softPedal = softPedal;
    }

    public String getSostenutoPedal() {
        return sostenutoPedal;
    }

    public void setSostenutoPedal(String sostenutoPedal) {
        this.sostenutoPedal = sostenutoPedal;
    }
}
