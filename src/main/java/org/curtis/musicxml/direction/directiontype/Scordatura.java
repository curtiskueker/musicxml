package org.curtis.musicxml.direction.directiontype;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("scordatura")
public class Scordatura extends DirectionType {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "scordatura_id", nullable = false)
    private List<Accord> accords = new ArrayList<>();

    public Scordatura() {

    }

    public List<Accord> getAccords() {
        return accords;
    }

    public void setAccords(List<Accord> accords) {
        this.accords = accords;
    }
}
