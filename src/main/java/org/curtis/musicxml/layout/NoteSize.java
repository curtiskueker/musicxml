package org.curtis.musicxml.layout;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.converter.NoteSizeTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "note_size")
public class NoteSize extends OrderedItem {
    @Column(precision = 12, scale = 4)
    private BigDecimal value;
    @Convert(converter = NoteSizeTypeConverter.class)
    @Column
    private NoteSizeType type;

    public NoteSize() {

    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public NoteSizeType getType() {
        return type;
    }

    public void setType(NoteSizeType type) {
        this.type = type;
    }
}
