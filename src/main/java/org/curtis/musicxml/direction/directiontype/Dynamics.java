package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.display.TextFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("dynamics")
public class Dynamics extends DirectionType {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "dynamics_id", nullable = false)
    @OrderBy("ordering")
    private List<DynamicsMarking> markings = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_format_id")
    private TextFormat textFormat;

    public Dynamics() {

    }

    public List<DynamicsMarking> getMarkings() {
        return markings;
    }

    public void setMarkings(List<DynamicsMarking> markings) {
        this.markings = markings;
    }

    public TextFormat getTextFormat() {
        return textFormat;
    }

    public void setTextFormat(TextFormat textFormat) {
        this.textFormat = textFormat;
    }
}
