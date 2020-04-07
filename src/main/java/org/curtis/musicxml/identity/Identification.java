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
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "identification")
public class Identification extends DatabaseItem {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "identification_id")
    private List<IdentificationType> identificationTypes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "identification_id", nullable = false)
    private List<Encoding> encodings = new ArrayList<>();
    @Column
    private String source;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "identification_id", nullable = false)
    private List<Miscellaneous> miscellaneousList = new ArrayList<>();

    public Identification() {

    }

    public List<IdentificationType> getIdentificationTypes() {
        return identificationTypes;
    }

    public void setIdentificationTypes(List<IdentificationType> identificationTypes) {
        this.identificationTypes = identificationTypes;
    }

    public List<IdentificationType> getCreators() {
        return getIdentificationTypes().stream().filter(idType -> idType.getIdName().equals("creator")).collect(Collectors.toList());
    }

    public List<IdentificationType> getRightsList() {
        return getIdentificationTypes().stream().filter(idType -> idType.getIdName().equals("rights")).collect(Collectors.toList());
    }

    public List<IdentificationType> getRelations() {
        return getIdentificationTypes().stream().filter(idType -> idType.getIdName().equals("relation")).collect(Collectors.toList());
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

    public List<Miscellaneous> getMiscellaneousList() {
        return miscellaneousList;
    }

    public void setMiscellaneousList(List<Miscellaneous> miscellaneousList) {
        this.miscellaneousList = miscellaneousList;
    }
}
