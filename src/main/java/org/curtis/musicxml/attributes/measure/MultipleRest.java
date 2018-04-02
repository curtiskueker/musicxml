package org.curtis.musicxml.attributes.measure;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("multiple rest")
public class MultipleRest extends MeasureStyle {
    @Transient
    private Integer value;
    @Transient
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
