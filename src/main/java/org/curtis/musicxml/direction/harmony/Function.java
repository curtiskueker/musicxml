package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.StyleText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;

@DiscriminatorValue("function")
public class Function extends HarmonyChord {
    @Transient
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
