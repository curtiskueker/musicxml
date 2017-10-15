package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.Tuning;

public class StaffTuning {
    private Tuning tuning;
    private Integer line;

    public StaffTuning() {

    }

    public Tuning getTuning() {
        return tuning;
    }

    public void setTuning(Tuning tuning) {
        this.tuning = tuning;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }
}
