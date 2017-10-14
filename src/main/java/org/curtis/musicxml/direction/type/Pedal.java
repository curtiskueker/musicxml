package org.curtis.musicxml.direction.type;

public class Pedal extends DirectionType {
    private String type;
    private Boolean line;
    private Boolean sign;
    // TODO: print style align

    public Pedal() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getLine() {
        return line;
    }

    public void setLine(Boolean line) {
        this.line = line;
    }

    public Boolean getSign() {
        return sign;
    }

    public void setSign(Boolean sign) {
        this.sign = sign;
    }
}
