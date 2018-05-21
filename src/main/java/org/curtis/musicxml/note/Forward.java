package org.curtis.musicxml.note;

import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("forward")
public class Forward extends MusicData {
    @Column
    private BigDecimal duration;
    @Transient
    private EditorialVoice editorialVoice;
    @Transient
    private Integer staff;

    public Forward() {

    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public EditorialVoice getEditorialVoice() {
        return editorialVoice;
    }

    public void setEditorialVoice(EditorialVoice editorialVoice) {
        this.editorialVoice = editorialVoice;
    }

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }
}
