package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "harmony_chord")
@DiscriminatorColumn(name = "harmony_chord_type")
public abstract class HarmonyChord extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kind_id")
    private Kind kind;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inversion_id")
    private Inversion inversion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bass_id")
    private Bass bass;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "harmony_chord_id", nullable = false)
    private List<Degree> degrees = new ArrayList<>();

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Inversion getInversion() {
        return inversion;
    }

    public void setInversion(Inversion inversion) {
        this.inversion = inversion;
    }

    public Bass getBass() {
        return bass;
    }

    public void setBass(Bass bass) {
        this.bass = bass;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }
}
