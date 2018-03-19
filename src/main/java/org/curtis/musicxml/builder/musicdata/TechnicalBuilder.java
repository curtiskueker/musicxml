package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.notation.technical.Arrow;
import org.curtis.musicxml.note.notation.technical.Bend;
import org.curtis.musicxml.note.notation.technical.HammerOn;
import org.curtis.musicxml.note.notation.technical.HammerOnPullOff;
import org.curtis.musicxml.note.notation.technical.Handbell;
import org.curtis.musicxml.note.notation.technical.Harmonic;
import org.curtis.musicxml.note.notation.technical.Heel;
import org.curtis.musicxml.note.notation.technical.HeelToe;
import org.curtis.musicxml.note.notation.technical.Hole;
import org.curtis.musicxml.note.notation.technical.Technical;
import org.curtis.util.StringUtil;

public class TechnicalBuilder extends BaseBuilder {
    private Technical technical;

    public TechnicalBuilder(Technical technical) {
        this.technical = technical;
    }

    public StringBuilder build() {
        if (technical == null) return stringBuilder;

        if (technical instanceof Harmonic) buildHarmonic((Harmonic)technical);
        else if (technical instanceof HammerOnPullOff) buildHammerOnPullOf((HammerOnPullOff)technical);
        else if (technical instanceof Bend) buildBend((Bend)technical);
        else if (technical instanceof HeelToe) buildHeelToe((HeelToe)technical);
        else if (technical instanceof Hole) buildHole((Hole)technical);
        else if (technical instanceof Arrow) buildArrow((Arrow)technical);
        else if (technical instanceof Handbell) buildHandbell((Handbell)technical);

        return stringBuilder;
    }

    private void buildHarmonic(Harmonic harmonic) {
        buildElement("harmonic");
    }

    private void buildHammerOnPullOf(HammerOnPullOff hammerOnPullOff) {
        String elementName = hammerOnPullOff instanceof HammerOn ? "hammer-on" : "pull-off";
        buildElementWithValue(elementName, hammerOnPullOff.getValue());
    }

    private void buildBend(Bend bend) {
        buildElement("bend");
    }

    private void buildHeelToe(HeelToe heelToe) {
        String elementName = heelToe instanceof Heel ? "heel" : "toe";
        buildElement(elementName);
    }

    private void buildHole(Hole hole) {
        appendLine("<hole>");
        String holeType = hole.getHoleType();
        if (StringUtil.isNotEmpty(holeType)) buildElementWithValue("hole-type", holeType);
        buildElementWithValueAndAttribute("hole-closed", BuilderUtil.enumValue(hole.getHoleClosedType()), "location", BuilderUtil.enumValue(hole.getHoleClosedLocation()));
        String holeShape = hole.getHoleShape();
        if (StringUtil.isNotEmpty(holeShape)) buildElementWithValue("hole-shape", holeShape);
        appendLine("</hole>");
    }

    private void buildArrow(Arrow arrow) {
        appendLine("<arrow>");
        buildElementWithValue("arrow-direction", BuilderUtil.enumValueWithSpaces(arrow.getArrowDirection()));
        buildElementWithValue("arrow-style", BuilderUtil.enumValue(arrow.getArrowStyle()));
        buildElementWithValue("circular-arrow", BuilderUtil.enumValue(arrow.getCircularArrow()));
        appendLine("</arrow>");
    }

    private void buildHandbell(Handbell handbell) {
        buildElementWithValue("handbell", BuilderUtil.enumValueWithSpaces(handbell.getHandbellType()));
    }
}
