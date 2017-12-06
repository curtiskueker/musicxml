package org.curtis.lilypond.builder.util;

import org.curtis.musicxml.score.Defaults;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;

public class ScoreDefaults {
    private static ScoreDefaults instance;
    private Defaults defaults;

    private ScoreDefaults() {
    }

    public static synchronized ScoreDefaults getInstance() {
        if (instance == null) {
            instance = new ScoreDefaults();
        }

        return instance;
    }

    public void setScoreDefaults(Defaults defaults) {
        this.defaults = defaults;
    }

    public boolean hasScaling() {
        return defaults != null && defaults.getScaling() != null;
    }

    public BigDecimal getScalingValue() {
        if(!hasScaling()) return MathUtil.ZERO;

        return MathUtil.divide(defaults.getScaling().getMillimeters(), defaults.getScaling().getTenths());
    }

    public BigDecimal getMillimeters(BigDecimal scaledValue) {
        if(scaledValue == null) return MathUtil.ZERO;
        if(!MathUtil.isPositive(getScalingValue())) return MathUtil.ZERO;

        return MathUtil.multiply(getScalingValue(), scaledValue);
    }
}
