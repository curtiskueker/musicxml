package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.GroupSymbolTypeConverter;
import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "part_symbol")
public class PartSymbol extends DatabaseItem {
    @Convert(converter = GroupSymbolTypeConverter.class)
    @Column
    private GroupSymbolType value;
    @Column(name = "top_staff")
    private Integer topStaff;
    @Column(name = "bottom_staff")
    private Integer bottomStaff;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public PartSymbol() {

    }

    public GroupSymbolType getValue() {
        return value;
    }

    public void setValue(GroupSymbolType value) {
        this.value = value;
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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
