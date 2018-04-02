package org.curtis.musicxml.attributes.time;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "interchangeable")
public class Interchangeable extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "time_relation")
    private TimeRelation timeRelation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_signature_id")
    private TimeSignature timeSignature;
    @Enumerated(EnumType.STRING)
    @Column
    private TimeSymbol symbol;
    @Enumerated(EnumType.STRING)
    @Column(name = "time_separator")
    private TimeSeparator separator;

    public Interchangeable() {

    }

    public TimeRelation getTimeRelation() {
        return timeRelation;
    }

    public void setTimeRelation(TimeRelation timeRelation) {
        this.timeRelation = timeRelation;
    }

    public TimeSignature getTimeSignature() {
        return timeSignature;
    }

    public void setTimeSignature(TimeSignature timeSignature) {
        this.timeSignature = timeSignature;
    }

    public TimeSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(TimeSymbol symbol) {
        this.symbol = symbol;
    }

    public TimeSeparator getSeparator() {
        return separator;
    }

    public void setSeparator(TimeSeparator separator) {
        this.separator = separator;
    }
}
