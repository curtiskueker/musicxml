package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tuplet_portion")
public class TupletPortion extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tuplet_number_id")
    private TupletNumber tupletNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tuplet_type_id")
    private TupletType tupletType;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "tuplet_portion_id", nullable = false)
    private List<TupletDot> tupletDots = new ArrayList<>();

    public TupletPortion() {

    }

    public TupletNumber getTupletNumber() {
        return tupletNumber;
    }

    public void setTupletNumber(TupletNumber tupletNumber) {
        this.tupletNumber = tupletNumber;
    }

    public TupletType getTupletType() {
        return tupletType;
    }

    public void setTupletType(TupletType tupletType) {
        this.tupletType = tupletType;
    }

    public List<TupletDot> getTupletDots() {
        return tupletDots;
    }

    public void setTupletDots(List<TupletDot> tupletDots) {
        this.tupletDots = tupletDots;
    }
}
