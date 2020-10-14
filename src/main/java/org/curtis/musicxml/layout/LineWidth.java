package org.curtis.musicxml.layout;

import org.curtis.database.OrderedItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "line_width")
public class LineWidth extends OrderedItem {
    @Column(precision = 12, scale = 4)
    private BigDecimal value;
    @Column
    private String type;

    public LineWidth() {

    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
