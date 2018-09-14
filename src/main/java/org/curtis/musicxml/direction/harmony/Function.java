package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.StyleText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("function")
public class Function extends HarmonyChord {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "function_id")
    private StyleText function;

    public Function() {

    }

    public StyleText getFunction() {
        return function;
    }

    public void setFunction(StyleText function) {
        this.function = function;
    }
}
