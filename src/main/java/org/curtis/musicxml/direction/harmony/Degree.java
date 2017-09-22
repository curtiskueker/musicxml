package org.curtis.musicxml.direction.harmony;

public class Degree {
    private DegreeValue degreeValue;
    private DegreeAlter degreeAlter;
    private DegreeType degreeType;

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
}
