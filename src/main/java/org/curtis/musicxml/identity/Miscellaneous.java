package org.curtis.musicxml.identity;

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
@Table(name = "miscellaneous")
public class Miscellaneous extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "miscellaneous_id", nullable = false)
    private List<MiscellaneousField> miscellaneousFields = new ArrayList<>();

    public Miscellaneous() {

    }

    public List<MiscellaneousField> getMiscellaneousFields() {
        return miscellaneousFields;
    }

    public void setMiscellaneousFields(List<MiscellaneousField> miscellaneousFields) {
        this.miscellaneousFields = miscellaneousFields;
    }
}
