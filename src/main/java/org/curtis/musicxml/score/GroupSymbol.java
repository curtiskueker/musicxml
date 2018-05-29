package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Position;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group_symbol")
public class GroupSymbol extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "group_symbol_type")
    private GroupSymbolType groupSymbolType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;
    @Column
    private String color;

    public GroupSymbol() {

    }

    public GroupSymbolType getGroupSymbolType() {
        return groupSymbolType;
    }

    public void setGroupSymbolType(GroupSymbolType groupSymbolType) {
        this.groupSymbolType = groupSymbolType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
