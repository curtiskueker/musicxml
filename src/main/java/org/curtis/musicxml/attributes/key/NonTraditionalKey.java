package org.curtis.musicxml.attributes.key;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("non-traditional key")
public class NonTraditionalKey extends Key {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "key_id", nullable = false)
    @OrderBy("ordering")
    private List<NonTraditionalKeyType> nonTraditionalKeyList = new ArrayList<>();

    public NonTraditionalKey() {

    }

    public List<NonTraditionalKeyType> getNonTraditionalKeyList() {
        return nonTraditionalKeyList;
    }

    public void setNonTraditionalKeyList(List<NonTraditionalKeyType> nonTraditionalKeyList) {
        this.nonTraditionalKeyList = nonTraditionalKeyList;
    }
}
