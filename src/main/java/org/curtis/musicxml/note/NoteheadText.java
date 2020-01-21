package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.TextDisplay;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notehead_text")
public class NoteheadText extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "notehead_text_id")
    private List<TextDisplay> textList = new ArrayList<>();

    public NoteheadText() {

    }

    public List<TextDisplay> getTextList() {
        return textList;
    }

    public void setTextList(List<TextDisplay> textList) {
        this.textList = textList;
    }
}
