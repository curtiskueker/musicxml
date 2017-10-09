package org.curtis.musicxml.attributes.key;

public class TraditionalKey {
    private Cancel cancel;
    private Integer fifths;
    private String mode;

    public TraditionalKey() {

    }

    public Cancel getCancel() {
        return cancel;
    }

    public void setCancel(Cancel cancel) {
        this.cancel = cancel;
    }

    public Integer getFifths() {
        return fifths;
    }

    public void setFifths(Integer fifths) {
        this.fifths = fifths;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
