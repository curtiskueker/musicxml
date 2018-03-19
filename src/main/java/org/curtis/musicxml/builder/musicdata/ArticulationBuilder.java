package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.articulation.BreathMark;
import org.curtis.musicxml.note.notation.articulation.StrongAccent;
import org.curtis.util.StringUtil;

public class ArticulationBuilder extends BaseBuilder {
    private Articulation articulation;

    public ArticulationBuilder(Articulation articulation) {
        this.articulation = articulation;
    }

    public StringBuilder build() {
        if (articulation == null) return stringBuilder;

        if (articulation instanceof StrongAccent) buildStrongAccent((StrongAccent)articulation);
        else if (articulation instanceof BreathMark) buildBreathMark((BreathMark)articulation);

        return stringBuilder;
    }

    private void buildStrongAccent(StrongAccent strongAccent) {
        buildElement("strong-accent");
    }

    private void buildBreathMark(BreathMark breathMark) {
        String elementName = "breath-mark";
        String breathMarkValue = BuilderUtil.enumValue(breathMark.getBreathMarkValue());
        if (StringUtil.isEmpty(breathMarkValue)) buildElement(elementName);
        else buildElementWithValue(elementName, breathMarkValue);
    }
}
