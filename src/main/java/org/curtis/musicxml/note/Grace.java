package org.curtis.musicxml.note;

import java.math.BigDecimal;

public class Grace {
    // TODO: steal time previous
    // TODO: steal time following
    private BigDecimal makeTime;
    private Boolean slash;

    public Grace() {

    }

    public BigDecimal getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(BigDecimal makeTime) {
        this.makeTime = makeTime;
    }

    public Boolean getSlash() {
        return slash;
    }

    public void setSlash(Boolean slash) {
        this.slash = slash;
    }
}
