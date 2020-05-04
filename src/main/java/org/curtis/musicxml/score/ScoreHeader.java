package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.identity.Identification;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "score_header")
public class ScoreHeader extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_id")
    private Work work;
    @Column(name = "movement_number")
    private String movementNumber;
    @Column(name = "movement_title")
    private String movementTitle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identification_id")
    private Identification identification;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "defaults_id")
    private Defaults defaults;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "score_header_id", nullable = false)
    @OrderBy("ordering")
    private List<Credit> credits = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "score_header_id", nullable = false)
    @OrderBy("ordering")
    private List<PartListItem> partListItems = new ArrayList<>();

    public ScoreHeader() {

    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public String getMovementNumber() {
        return movementNumber;
    }

    public void setMovementNumber(String movementNumber) {
        this.movementNumber = movementNumber;
    }

    public String getMovementTitle() {
        return movementTitle;
    }

    public void setMovementTitle(String movementTitle) {
        this.movementTitle = movementTitle;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public Defaults getDefaults() {
        return defaults;
    }

    public void setDefaults(Defaults defaults) {
        this.defaults = defaults;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public List<PartListItem> getPartListItems() {
        return partListItems;
    }

    public void setPartListItems(List<PartListItem> partListItems) {
        this.partListItems = partListItems;
    }
}
