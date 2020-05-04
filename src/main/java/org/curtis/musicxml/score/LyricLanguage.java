package org.curtis.musicxml.score;

import org.curtis.database.OrderedItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lyric_language")
public class LyricLanguage extends OrderedItem {
    @Column(name = "lyric_language_number")
    private String number;
    @Column
    private String name;
    @Column
    private String lang;

    public LyricLanguage() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
