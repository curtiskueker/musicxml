package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.attributes.Tuning;

public class Accord {
    private Tuning tuning;
    private Integer string;

    public Accord() {

    }

    public Tuning getTuning() {
        return tuning;
    }

    public void setTuning(Tuning tuning) {
        this.tuning = tuning;
    }

    public Integer getString() {
        return string;
    }

    public void setString(Integer string) {
        this.string = string;
    }
}
