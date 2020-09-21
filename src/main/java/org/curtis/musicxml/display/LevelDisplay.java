package org.curtis.musicxml.display;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.SymbolSize;
import org.curtis.musicxml.converter.SymbolSizeConverter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "level_display")
public class LevelDisplay extends DatabaseItem {
    @Column
    @Type(type="yes_no")
    private Boolean parentheses;
    @Column
    @Type(type="yes_no")
    private Boolean bracket;
    @Convert(converter = SymbolSizeConverter.class)
    @Column(name = "symbol_size")
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
