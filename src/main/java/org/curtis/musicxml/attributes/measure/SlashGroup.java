package org.curtis.musicxml.attributes.measure;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.NoteTypeValue;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "slash_group")
public class SlashGroup extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "slash_type")
    private NoteTypeValue slashType;
    @Column(name = "slash_dots")
    private Integer slashDots = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "slash_group_id")
    @OrderBy("ordering")
    private List<ExceptVoice> exceptVoices = new ArrayList<>();


    public SlashGroup() {

    }

    public NoteTypeValue getSlashType() {
        return slashType;
    }

    public void setSlashType(NoteTypeValue slashType) {
        this.slashType = slashType;
    }

    public Integer getSlashDots() {
        return slashDots;
    }

    public void setSlashDots(Integer slashDots) {
        this.slashDots = slashDots;
    }

    public List<ExceptVoice> getExceptVoices() {
        return exceptVoices;
    }

    public void setExceptVoices(List<ExceptVoice> exceptVoices) {
        this.exceptVoices = exceptVoices;
    }
}
