package org.curtis.musicxml.display;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "name_display")
public class NameDisplay extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "name_display_id")
    private List<TextDisplay> textList = new ArrayList<>();
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;

    public NameDisplay() {

    }

    public List<TextDisplay> getTextList() {
        return textList;
    }

    public void setTextList(List<TextDisplay> textList) {
        this.textList = textList;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
