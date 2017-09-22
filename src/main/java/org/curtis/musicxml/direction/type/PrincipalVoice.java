package org.curtis.musicxml.direction.type;

public class PrincipalVoice extends DirectionType {
    private String principalVoice;
    // TODO: type
    private String symbol;
    // TODO: print style align

    public PrincipalVoice() {

    }

    public String getPrincipalVoice() {
        return principalVoice;
    }

    public void setPrincipalVoice(String principalVoice) {
        this.principalVoice = principalVoice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
