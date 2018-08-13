package org.curtis.musicxml.identity;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "typed_text")
public class TypedText extends DatabaseItem {
    @Column
    private String value;
    @Column
    private String type;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Identification creator;
    @ManyToOne
    @JoinColumn(name = "rights_id")
    private Identification rights;
    @ManyToOne
    @JoinColumn(name = "relation_id")
    private Identification relation;

    public TypedText() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Identification getCreator() {
        return creator;
    }

    public void setCreator(Identification creator) {
        this.creator = creator;
    }

    public Identification getRights() {
        return rights;
    }

    public void setRights(Identification rights) {
        this.rights = rights;
    }

    public Identification getRelation() {
        return relation;
    }

    public void setRelation(Identification relation) {
        this.relation = relation;
    }
}
