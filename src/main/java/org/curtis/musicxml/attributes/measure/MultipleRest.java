package org.curtis.musicxml.attributes.measure;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("multiple rest")
public class MultipleRest extends MeasureStyle {
    @Column
    private Integer value;
    @Column(name = "use_symbols")
    @Type(type="yes_no")
    private Boolean useSymbols;

    public MultipleRest() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getUseSymbols() {
        return useSymbols;
    }

    public void setUseSymbols(Boolean useSymbols) {
        this.useSymbols = useSymbols;
    }
}
