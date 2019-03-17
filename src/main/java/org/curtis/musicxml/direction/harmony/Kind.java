package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kind")
public class Kind extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "kind_value")
    private KindValue kindValue;
    @Column(name = "use_symbols")
    @Type(type="yes_no")
    private Boolean useSymbols;
    @Column
    private String text;
    @Column(name = "stack_degrees")
    @Type(type="yes_no")
    private Boolean stackDegrees;
    @Column(name = "parentheses_degrees")
    @Type(type="yes_no")
    private Boolean parenthesesDegrees;
    @Column(name = "bracket_degrees")
    @Type(type="yes_no")
    private Boolean bracketDegrees;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "print_style_id")
    private PrintStyle printStyle;
    @Enumerated(EnumType.STRING)
    @Column
    private Location halign;
    @Enumerated(EnumType.STRING)
    @Column
    private Location valign;

    public Kind() {

    }

    public KindValue getKindValue() {
        return kindValue;
    }

    public void setKindValue(KindValue kindValue) {
        this.kindValue = kindValue;
    }

    public Boolean getUseSymbols() {
        return useSymbols;
    }

    public void setUseSymbols(Boolean useSymbols) {
        this.useSymbols = useSymbols;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getStackDegrees() {
        return stackDegrees;
    }

    public void setStackDegrees(Boolean stackDegrees) {
        this.stackDegrees = stackDegrees;
    }

    public Boolean getParenthesesDegrees() {
        return parenthesesDegrees;
    }

    public void setParenthesesDegrees(Boolean parenthesesDegrees) {
        this.parenthesesDegrees = parenthesesDegrees;
    }

    public Boolean getBracketDegrees() {
        return bracketDegrees;
    }

    public void setBracketDegrees(Boolean bracketDegrees) {
        this.bracketDegrees = bracketDegrees;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Location getHalign() {
        return halign;
    }

    public void setHalign(Location halign) {
        this.halign = halign;
    }

    public Location getValign() {
        return valign;
    }

    public void setValign(Location valign) {
        this.valign = valign;
    }
}
