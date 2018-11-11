package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "part_list")
public class PartList extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "part_list_id", nullable = false)
    @OrderBy("ordering")
    private List<PartItem> partItems = new ArrayList<>();

    public PartList() {

    }

    public List<PartItem> getPartItems() {
        return partItems;
    }

    public void setPartItems(List<PartItem> partItems) {
        this.partItems = partItems;
    }
}
