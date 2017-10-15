package org.curtis.musicxml.attributes.measure;

public class MultipleRest extends MeasureStyle {
    private Integer value;
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
