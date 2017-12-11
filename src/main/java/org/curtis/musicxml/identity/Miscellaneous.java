package org.curtis.musicxml.identity;

import java.util.ArrayList;
import java.util.List;

public class Miscellaneous {
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
