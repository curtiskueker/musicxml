package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.musicxml.note.notation.technical.Arrow;
import org.curtis.musicxml.note.notation.technical.Bend;
import org.curtis.musicxml.note.notation.technical.DoubleTongue;
import org.curtis.musicxml.note.notation.technical.DownBow;
import org.curtis.musicxml.note.notation.technical.Fingernails;
import org.curtis.musicxml.note.notation.technical.HammerOn;
import org.curtis.musicxml.note.notation.technical.HammerOnPullOff;
import org.curtis.musicxml.note.notation.technical.Handbell;
import org.curtis.musicxml.note.notation.technical.Harmonic;
import org.curtis.musicxml.note.notation.technical.Heel;
import org.curtis.musicxml.note.notation.technical.HeelToe;
import org.curtis.musicxml.note.notation.technical.Hole;
import org.curtis.musicxml.note.notation.technical.OpenString;
import org.curtis.musicxml.note.notation.technical.OtherTechnical;
import org.curtis.musicxml.note.notation.technical.Pluck;
import org.curtis.musicxml.note.notation.technical.SnapPizzicato;
import org.curtis.musicxml.note.notation.technical.Stopped;
import org.curtis.musicxml.note.notation.technical.Tap;
import org.curtis.musicxml.note.notation.technical.Technical;
import org.curtis.musicxml.note.notation.technical.ThumbPosition;
import org.curtis.musicxml.note.notation.technical.TripleTongue;
import org.curtis.musicxml.note.notation.technical.UpBow;
import org.curtis.util.StringUtil;

public class TechnicalBuilder extends BaseBuilder {
    private Technical technical;

    public TechnicalBuilder(Technical technical) {
        this.technical = technical;
    }

    public StringBuilder build() {
        if (technical == null) return stringBuilder;

        if (technical instanceof UpBow) buildUpBow((UpBow)technical);
        else if ((technical instanceof DownBow)) buildDownBow((DownBow)technical);
        else if (technical instanceof Harmonic) buildHarmonic((Harmonic)technical);
        else if (technical instanceof OpenString) buildOpenString((OpenString)technical);
        else if (technical instanceof ThumbPosition) buildThumbPosition((ThumbPosition)technical);
        else if (technical instanceof Pluck) buildPluck((Pluck)technical);
        else if (technical instanceof DoubleTongue) buildDoubleTongue((DoubleTongue)technical);
        else if (technical instanceof TripleTongue) buildTripleTongue((TripleTongue)technical);
        else if (technical instanceof Stopped) buildStopped((Stopped)technical);
        else if (technical instanceof SnapPizzicato) buildSnapPizzicato((SnapPizzicato)technical);
        else if (technical instanceof HammerOnPullOff) buildHammerOnPullOf((HammerOnPullOff)technical);
        else if (technical instanceof Bend) buildBend((Bend)technical);
        else if (technical instanceof Tap) buildTap((Tap)technical);
        else if (technical instanceof HeelToe) buildHeelToe((HeelToe)technical);
        else if (technical instanceof Fingernails) buildFingernails((Fingernails)technical);
        else if (technical instanceof Hole) buildHole((Hole)technical);
        else if (technical instanceof Arrow) buildArrow((Arrow)technical);
        else if (technical instanceof Handbell) buildHandbell((Handbell)technical);
        else if (technical instanceof OtherTechnical) buildOtherTechnical((OtherTechnical)technical);

        return stringBuilder;
    }

    private void buildUpBow(UpBow upBow) {
        buildPlacement("up-bow", upBow.getPlacement());
    }

    private void buildDownBow(DownBow downBow) {
        buildPlacement("down-bow", downBow.getPlacement());
    }

    private void buildHarmonic(Harmonic harmonic) {
        buildElement("harmonic");
    }

    private void buildOpenString(OpenString openString) {
        buildPlacement("down-bow", openString.getPlacement());
    }

    private void buildThumbPosition(ThumbPosition thumbPosition) {
        buildPlacement("thumb-position", thumbPosition.getPlacement());
    }

    private void buildPluck(Pluck pluck) {
        buildPlacementText("pluck", pluck.getPlacementText());
    }

    private void buildDoubleTongue(DoubleTongue doubleTongue) {
        buildPlacement("double-tongue", doubleTongue.getPlacement());
    }

    private void buildTripleTongue(TripleTongue tripleTongue) {
        buildPlacement("triple-tongue", tripleTongue.getPlacement());
    }

    private void buildStopped(Stopped stopped) {
        buildPlacement("stopped", stopped.getPlacement());
    }

    private void buildSnapPizzicato(SnapPizzicato snapPizzicato) {
        buildPlacement("snap-pizzicato", snapPizzicato.getPlacement());
    }

    private void buildHammerOnPullOf(HammerOnPullOff hammerOnPullOff) {
        String elementName = hammerOnPullOff instanceof HammerOn ? "hammer-on" : "pull-off";
        buildElementWithValue(elementName, hammerOnPullOff.getValue());
    }

    private void buildBend(Bend bend) {
        appendLine("<bend>");
        PlacementText withBar = bend.getWithBar();
        if (withBar != null) buildPlacementText("with-bar", withBar);
        appendLine("</bend>");
    }

    private void buildTap(Tap tap) {
        buildPlacementText("tap", tap.getPlacementText());
    }

    private void buildHeelToe(HeelToe heelToe) {
        String elementName = heelToe instanceof Heel ? "heel" : "toe";
        buildPlacement(elementName, heelToe.getPlacement());
    }

    private void buildFingernails(Fingernails fingernails) {
        buildPlacement("fingernails", fingernails.getPlacement());
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

    private void buildOtherTechnical(OtherTechnical otherTechnical) {
        buildPlacementText("other-technical", otherTechnical.getPlacementText());
    }
}
