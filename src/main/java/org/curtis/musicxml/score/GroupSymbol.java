package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Display;

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
    @JoinColumn(name = "display_id")
    private Display display;

    public GroupSymbol() {

    }

    public GroupSymbolType getGroupSymbolType() {
        return groupSymbolType;
    }

    public void setGroupSymbolType(GroupSymbolType groupSymbolType) {
        this.groupSymbolType = groupSymbolType;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
