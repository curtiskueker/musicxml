package org.curtis.musicxml.identity;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.identity.encoding.Encoding;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "identification")
public class Identification extends DatabaseItem {
    @Transient
    private List<TypedText> creators = new ArrayList<>();
    @Transient
    private List<TypedText> rightsList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "identification_id", nullable = false)
    private List<Encoding> encodings = new ArrayList<>();
    @Column
    private String source;
    @Transient
    private List<TypedText> relations = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "miscellaneous_id")
    private Miscellaneous miscellaneous;

    public Identification() {

    }

    public List<TypedText> getCreators() {
        return creators;
    }

    public void setCreators(List<TypedText> creators) {
        this.creators = creators;
    }

    public List<TypedText> getRightsList() {
        return rightsList;
    }

    public void setRightsList(List<TypedText> rightsList) {
        this.rightsList = rightsList;
    }

    public List<Encoding> getEncodings() {
        return encodings;
    }

    public void setEncodings(List<Encoding> encodings) {
        this.encodings = encodings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<TypedText> getRelations() {
        return relations;
    }

    public void setRelations(List<TypedText> relations) {
        this.relations = relations;
    }

    public Miscellaneous getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Miscellaneous miscellaneous) {
        this.miscellaneous = miscellaneous;
    }
}
