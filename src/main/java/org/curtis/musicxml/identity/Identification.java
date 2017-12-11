package org.curtis.musicxml.identity;

import org.curtis.musicxml.identity.encoding.Encoding;

import java.util.ArrayList;
import java.util.List;

public class Identification {
    private List<TypedText> creators = new ArrayList<>();
    private List<TypedText> rightsList = new ArrayList<>();
    private List<Encoding> encodings = new ArrayList<>();
    private String source;
    private List<TypedText> relations = new ArrayList<>();
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
