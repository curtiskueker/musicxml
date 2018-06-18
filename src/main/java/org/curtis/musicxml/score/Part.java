package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "part")
public class Part extends DatabaseItem {
    @Column(name = "part_id")
    private String partId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "part_id", nullable = false)
    private List<Measure> measures = new ArrayList<>();

    public Part() {

    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }
}
