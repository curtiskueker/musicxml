package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "note_size")
public class NoteSize extends DatabaseItem {
    @Column(precision = 12, scale = 4)
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
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
