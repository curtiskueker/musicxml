package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.EnclosureShape;
import org.curtis.musicxml.common.TextDecoration;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("dynamics")
public class Dynamics extends DirectionType {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "dynamics_id", nullable = false)
    private List<DynamicsMarking> markings = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_decoration_id")
    private TextDecoration textDecoration;
    @Enumerated(EnumType.STRING)
    @Column
    private EnclosureShape enclosure;

    public Dynamics() {

    }

    public List<DynamicsMarking> getMarkings() {
        return markings;
    }

    public void setMarkings(List<DynamicsMarking> markings) {
        this.markings = markings;
    }

    public TextDecoration getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(TextDecoration textDecoration) {
        this.textDecoration = textDecoration;
    }

    public EnclosureShape getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EnclosureShape enclosure) {
        this.enclosure = enclosure;
    }
}
