package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "level_display")
public class LevelDisplay extends DatabaseItem {
    @Column
    private Boolean parentheses;
    @Column
    private Boolean bracket;
    @Enumerated(EnumType.STRING)
    @Column
    private SymbolSize size;

    public LevelDisplay() {

    }

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
    }

    public Boolean getBracket() {
        return bracket;
    }

    public void setBracket(Boolean bracket) {
        this.bracket = bracket;
    }

    public SymbolSize getSize() {
        return size;
    }

    public void setSize(SymbolSize size) {
        this.size = size;
    }
}
