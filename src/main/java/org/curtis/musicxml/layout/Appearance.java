package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appearance")
public class Appearance extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "appearance_id", nullable = false)
    private List<LineWidth> lineWidths = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "appearance_id", nullable = false)
    private List<NoteSize> noteSizes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "appearance_id", nullable = false)
    private List<Distance> distances = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "appearance_id", nullable = false)
    private List<OtherAppearance> otherAppearances = new ArrayList<>();

    public Appearance() {

    }

    public List<LineWidth> getLineWidths() {
        return lineWidths;
    }

    public void setLineWidths(List<LineWidth> lineWidths) {
        this.lineWidths = lineWidths;
    }

    public List<NoteSize> getNoteSizes() {
        return noteSizes;
    }

    public void setNoteSizes(List<NoteSize> noteSizes) {
        this.noteSizes = noteSizes;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    public List<OtherAppearance> getOtherAppearances() {
        return otherAppearances;
    }

    public void setOtherAppearances(List<OtherAppearance> otherAppearances) {
        this.otherAppearances = otherAppearances;
    }
}
