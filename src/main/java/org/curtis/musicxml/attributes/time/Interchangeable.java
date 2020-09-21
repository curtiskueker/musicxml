package org.curtis.musicxml.attributes.time;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.TimeRelationConverter;
import org.curtis.musicxml.converter.TimeSeparatorConverter;
import org.curtis.musicxml.converter.TimeSymbolConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "interchangeable")
public class Interchangeable extends DatabaseItem {
    @Column(name = "time_relation")
    @Convert(converter = TimeRelationConverter.class)
    private TimeRelation timeRelation;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "interchangeable_id")
    @OrderBy("ordering")
    private List<TimeSignatureType> timeSignatureList = new ArrayList<>();
    @Convert(converter = TimeSymbolConverter.class)
    @Column
    private TimeSymbol symbol;
    @Convert(converter = TimeSeparatorConverter.class)
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

    public List<TimeSignatureType> getTimeSignatureList() {
        return timeSignatureList;
    }

    public void setTimeSignatureList(List<TimeSignatureType> timeSignatureList) {
        this.timeSignatureList = timeSignatureList;
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
