package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.converter.ConnectionConverter;
import org.curtis.musicxml.converter.ShowTupletConverter;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.notation.ShowTuplet;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "metronome_tuplet")
public class MetronomeTuplet extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_modification_id")
    private TimeModification timeModification;
    @Convert(converter = ConnectionConverter.class)
    @Column
    private Connection type;
    @Column
    @Type(type="yes_no")
    private Boolean bracket;
    @Convert(converter = ShowTupletConverter.class)
    @Column(name = "show_number")
    private ShowTuplet showNumber;

    public MetronomeTuplet() {

    }

    public TimeModification getTimeModification() {
        return timeModification;
    }

    public void setTimeModification(TimeModification timeModification) {
        this.timeModification = timeModification;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Boolean getBracket() {
        return bracket;
    }

    public void setBracket(Boolean bracket) {
        this.bracket = bracket;
    }

    public ShowTuplet getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(ShowTuplet showNumber) {
        this.showNumber = showNumber;
    }
}
