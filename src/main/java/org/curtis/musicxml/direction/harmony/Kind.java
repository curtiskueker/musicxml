package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.KindValueConverter;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kind")
public class Kind extends DatabaseItem {
    @Convert(converter = KindValueConverter.class)
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
    @JoinColumn(name = "display_id")
    private Display display;

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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
