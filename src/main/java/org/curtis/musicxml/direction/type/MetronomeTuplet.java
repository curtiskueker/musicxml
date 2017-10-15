package org.curtis.musicxml.direction.type;

public class MetronomeTuplet {
    // TODO: time modification
    private String type;
    private Boolean bracket;
    private String showNumber;

    public MetronomeTuplet() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getBracket() {
        return bracket;
    }

    public void setBracket(Boolean bracket) {
        this.bracket = bracket;
    }

    public String getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(String showNumber) {
        this.showNumber = showNumber;
    }
}
