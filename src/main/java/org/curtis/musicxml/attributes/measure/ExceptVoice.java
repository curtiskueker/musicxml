package org.curtis.musicxml.attributes.measure;

import org.curtis.database.OrderedItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "except_voice")
public class ExceptVoice extends OrderedItem {
    @Column
    private String value;

    public ExceptVoice() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
