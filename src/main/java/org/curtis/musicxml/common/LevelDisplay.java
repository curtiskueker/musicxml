package org.curtis.musicxml.common;

public class LevelDisplay {
    private Boolean parentheses;
    private Boolean bracket;
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
