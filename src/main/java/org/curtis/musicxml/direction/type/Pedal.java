package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.PrintStyleAlign;

public class Pedal extends DirectionType {
    private String type;
    private Boolean line;
    private Boolean sign;
    private PrintStyleAlign printStyleAlign;

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

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
    }
}
