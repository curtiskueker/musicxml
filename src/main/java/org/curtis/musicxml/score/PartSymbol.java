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
import javax.persistence.Transient;

@Entity
@Table(name = "part_symbol")
public class PartSymbol extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "group_symbol_type")
    private GroupSymbolType groupSymbolType;
    @Transient
    private Integer topStaff;
    @Transient
    private Integer bottomStaff;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;
    @Column
    private String color;

    public PartSymbol() {

    }

    public GroupSymbolType getGroupSymbolType() {
        return groupSymbolType;
    }

    public void setGroupSymbolType(GroupSymbolType groupSymbolType) {
        this.groupSymbolType = groupSymbolType;
    }

    public Integer getTopStaff() {
        return topStaff;
    }

    public void setTopStaff(Integer topStaff) {
        this.topStaff = topStaff;
    }

    public Integer getBottomStaff() {
        return bottomStaff;
    }

    public void setBottomStaff(Integer bottomStaff) {
        this.bottomStaff = bottomStaff;
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
