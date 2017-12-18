package org.curtis.musicxml.score;

import java.util.ArrayList;
import java.util.List;

public class Part {
    private String id;
    private List<Measure> measures = new ArrayList<>();
    private Integer staffNumber;

    public Part() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }
}
