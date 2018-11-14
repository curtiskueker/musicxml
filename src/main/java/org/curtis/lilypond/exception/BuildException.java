package org.curtis.lilypond.exception;

import org.curtis.util.MathUtil;

import java.math.BigDecimal;

public class BuildException extends LilypondException {
    private BigDecimal unhandledDuration = MathUtil.ZERO;

    public BuildException(String message) {
        super(message);
    }

    public BuildException(Exception e) {
        super(e);
    }

    public BigDecimal getUnhandledDuration() {
        return unhandledDuration;
    }

    public void setUnhandledDuration(BigDecimal unhandledDuration) {
        this.unhandledDuration = unhandledDuration;
    }
}
