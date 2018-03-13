package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Degree extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "degree_value_id")
    private DegreeValue degreeValue;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "degree_alter_id")
    private DegreeAlter degreeAlter;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "degree_type_id")
    private DegreeType degreeType;
    @Transient
    private Boolean printObject;

    public Degree() {

    }

    public DegreeValue getDegreeValue() {
        return degreeValue;
    }

    public void setDegreeValue(DegreeValue degreeValue) {
        this.degreeValue = degreeValue;
    }

    public DegreeAlter getDegreeAlter() {
        return degreeAlter;
    }

    public void setDegreeAlter(DegreeAlter degreeAlter) {
        this.degreeAlter = degreeAlter;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
