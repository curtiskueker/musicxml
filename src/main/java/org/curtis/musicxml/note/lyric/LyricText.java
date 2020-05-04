package org.curtis.musicxml.note.lyric;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("lyric text")
public class LyricText extends LyricItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "lyric_text_id", nullable = false)
    @OrderBy("ordering")
    private List<LyricSyllable> lyricSyllables = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "extend_id")
    private Extend extend;

    public LyricText() {

    }

    public List<LyricSyllable> getLyricSyllables() {
        return lyricSyllables;
    }

    public void setLyricSyllables(List<LyricSyllable> lyricSyllables) {
        this.lyricSyllables = lyricSyllables;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }
}
