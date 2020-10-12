package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.note.notation.technical.Arrow;
import org.curtis.musicxml.note.notation.technical.Bend;
import org.curtis.musicxml.note.notation.technical.BendSound;
import org.curtis.musicxml.note.notation.technical.BendType;
import org.curtis.musicxml.note.notation.technical.BendWithBar;
import org.curtis.musicxml.note.notation.technical.BrassBend;
import org.curtis.musicxml.note.notation.technical.DoubleTongue;
import org.curtis.musicxml.note.notation.technical.DownBow;
import org.curtis.musicxml.note.notation.technical.Fingering;
import org.curtis.musicxml.note.notation.technical.Fingernails;
import org.curtis.musicxml.note.notation.technical.Flip;
import org.curtis.musicxml.note.notation.technical.Fret;
import org.curtis.musicxml.note.notation.technical.Golpe;
import org.curtis.musicxml.note.notation.technical.HalfMuted;
import org.curtis.musicxml.note.notation.technical.HammerOn;
import org.curtis.musicxml.note.notation.technical.HammerOnPullOff;
import org.curtis.musicxml.note.notation.technical.Handbell;
import org.curtis.musicxml.note.notation.technical.HarmonMute;
import org.curtis.musicxml.note.notation.technical.Harmonic;
import org.curtis.musicxml.note.notation.technical.HarmonicPitch;
import org.curtis.musicxml.note.notation.technical.HarmonicType;
import org.curtis.musicxml.note.notation.technical.Heel;
import org.curtis.musicxml.note.notation.technical.HeelToe;
import org.curtis.musicxml.note.notation.technical.Hole;
import org.curtis.musicxml.note.notation.technical.Open;
import org.curtis.musicxml.note.notation.technical.OpenString;
import org.curtis.musicxml.note.notation.technical.OtherTechnical;
import org.curtis.musicxml.note.notation.technical.Pluck;
import org.curtis.musicxml.note.notation.technical.Smear;
import org.curtis.musicxml.note.notation.technical.SnapPizzicato;
import org.curtis.musicxml.note.notation.technical.Stopped;
import org.curtis.musicxml.note.notation.technical.StringNumber;
import org.curtis.musicxml.note.notation.technical.Tap;
import org.curtis.musicxml.note.notation.technical.Technical;
import org.curtis.musicxml.note.notation.technical.ThumbPosition;
import org.curtis.musicxml.note.notation.technical.TripleTongue;
import org.curtis.musicxml.note.notation.technical.UpBow;
import org.curtis.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class TechnicalBuilder extends MusicDataBuilder {
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
        else if (technical instanceof Fingering) buildFingering((Fingering)technical);
        else if (technical instanceof Pluck) buildPluck((Pluck)technical);
        else if (technical instanceof DoubleTongue) buildDoubleTongue((DoubleTongue)technical);
        else if (technical instanceof TripleTongue) buildTripleTongue((TripleTongue)technical);
        else if (technical instanceof Stopped) buildStopped((Stopped)technical);
        else if (technical instanceof SnapPizzicato) buildSnapPizzicato((SnapPizzicato)technical);
        else if (technical instanceof Fret) buildFret((Fret)technical);
        else if (technical instanceof StringNumber) buildStringNumber((StringNumber)technical);
        else if (technical instanceof HammerOnPullOff) buildHammerOnPullOf((HammerOnPullOff)technical);
        else if (technical instanceof Bend) buildBend((Bend)technical);
        else if (technical instanceof Tap) buildTap((Tap)technical);
        else if (technical instanceof HeelToe) buildHeelToe((HeelToe)technical);
        else if (technical instanceof Fingernails) buildFingernails((Fingernails)technical);
        else if (technical instanceof Hole) buildHole((Hole)technical);
        else if (technical instanceof Arrow) buildArrow((Arrow)technical);
        else if (technical instanceof Handbell) buildHandbell((Handbell)technical);
        else if (technical instanceof OtherTechnical) buildOtherTechnical((OtherTechnical)technical);
        else if (technical instanceof BrassBend) buildBrassBend((BrassBend)technical);
        else if (technical instanceof Flip) buildFlip((Flip)technical);
        else if (technical instanceof Smear) buildSmear((Smear)technical);
        else if (technical instanceof Open) buildOpen((Open)technical);
        else if (technical instanceof HalfMuted) buildHalfMuted((HalfMuted)technical);
        else if (technical instanceof HarmonMute) buildHarmonMute((HarmonMute)technical);
        else if (technical instanceof Golpe) buildGolpe((Golpe)technical);

        return stringBuilder;
    }

    private void buildUpBow(UpBow upBow) {
        buildElementWithOptionalAttributes("up-bow", DisplayBuilder.buildDisplay(upBow.getDisplay()));
    }

    private void buildDownBow(DownBow downBow) {
        buildElementWithOptionalAttributes("down-bow", DisplayBuilder.buildDisplay(downBow.getDisplay()));
    }

    private void buildHarmonic(Harmonic harmonic) {
        buildOpenElement("harmonic");
        buildAttribute("print-object",  harmonic.getPrintObject());
        buildAttributes(DisplayBuilder.buildDisplay(harmonic.getDisplay()));
        buildCloseElement();
        HarmonicType harmonicType = harmonic.getType();
        if (harmonicType != null) buildElement(harmonicType.toString().toLowerCase());
        HarmonicPitch harmonicPitch = harmonic.getHarmonicPitch();
        if (harmonicPitch != null) buildElement(harmonicPitch.toString().toLowerCase().replace("_", "-"));
        buildEndElement("harmonic");
    }

    private void buildOpenString(OpenString openString) {
        buildElementWithOptionalAttributes("down-bow", DisplayBuilder.buildDisplay(openString.getDisplay()));
    }

    private void buildThumbPosition(ThumbPosition thumbPosition) {
        buildElementWithOptionalAttributes("thumb-position", DisplayBuilder.buildDisplay(thumbPosition.getDisplay()));
    }

    private void buildPluck(Pluck pluck) {
        buildElementWithValueAndAttributes("pluck", pluck.getValue(), DisplayBuilder.buildDisplay(pluck.getDisplay()));
    }

    private void buildDoubleTongue(DoubleTongue doubleTongue) {
        buildElementWithOptionalAttributes("double-tongue", DisplayBuilder.buildDisplay(doubleTongue.getDisplay()));
    }

    private void buildTripleTongue(TripleTongue tripleTongue) {
        buildElementWithOptionalAttributes("triple-tongue", DisplayBuilder.buildDisplay(tripleTongue.getDisplay()));
    }

    private void buildStopped(Stopped stopped) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(stopped.getDisplay()));
        attributes.put("smufl", stopped.getSmufl());
        buildElementWithOptionalAttributes("stopped", attributes);
    }

    private void buildSnapPizzicato(SnapPizzicato snapPizzicato) {
        buildElementWithOptionalAttributes("snap-pizzicato", DisplayBuilder.buildDisplay(snapPizzicato.getDisplay()));
    }

    private void buildHammerOnPullOf(HammerOnPullOff hammerOnPullOff) {
        String elementName = hammerOnPullOff instanceof HammerOn ? "hammer-on" : "pull-off";
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(hammerOnPullOff.getType()));
        attributes.put("number", BuilderUtil.stringValue(hammerOnPullOff.getNumber()));
        attributes.putAll(DisplayBuilder.buildDisplay(hammerOnPullOff.getDisplay()));
        buildElementWithValueAndAttributes(elementName, hammerOnPullOff.getValue(), attributes);
    }

    private void buildBend(Bend bend) {
        buildOpenElement("bend");
        buildAttributes(DisplayBuilder.buildDisplay(bend.getDisplay()));
        buildAttributes(buildBendSound(bend.getBendSound()));
        buildCloseElement();
        buildElementWithValue("bend-alter", bend.getBendAlter());
        BendType bendType = bend.getType();
        if (bendType != null) buildElement(bendType.toString().toLowerCase().replace("_", "-"));
        BendWithBar withBar = bend.getWithBar();
        if (withBar != null) buildElementWithValueAndAttributes("with-bar", withBar.getValue(), DisplayBuilder.buildDisplay(withBar.getDisplay()));
        buildEndElement("bend");
    }

    private void buildTap(Tap tap) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(tap.getDisplay()));
        attributes.put("hand", BuilderUtil.enumValue(tap.getTapHand()));
        buildElementWithValueAndAttributes("tap", tap.getValue(), attributes);
    }

    private void buildHeelToe(HeelToe heelToe) {
        String elementName = heelToe instanceof Heel ? "heel" : "toe";
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(heelToe.getDisplay()));
        attributes.put("substitution", BuilderUtil.yesOrNo(heelToe.getSubstitution()));
        buildElementWithAttributes(elementName, attributes);
    }

    private void buildFingernails(Fingernails fingernails) {
        buildElementWithOptionalAttributes("fingernails", DisplayBuilder.buildDisplay(fingernails.getDisplay()));
    }

    private void buildHole(Hole hole) {
        buildOpenElement("hole");
        buildAttributes(DisplayBuilder.buildDisplay(hole.getDisplay()));
        buildCloseElement();
        String holeType = hole.getType();
        if (StringUtil.isNotEmpty(holeType)) buildElementWithValue("hole-type", holeType);
        buildElementWithValueAndAttribute("hole-closed", hole.getHoleClosed(), "location", hole.getHoleClosedLocation());
        String holeShape = hole.getHoleShape();
        if (StringUtil.isNotEmpty(holeShape)) buildElementWithValue("hole-shape", holeShape);
        buildEndElement("hole");
    }

    private void buildArrow(Arrow arrow) {
        buildOpenElement("arrow");
        buildAttributes(DisplayBuilder.buildDisplay(arrow.getDisplay()));
        buildAttribute("smufl", arrow.getSmufl());
        buildCloseElement();
        buildElementWithValue("arrow-direction", BuilderUtil.enumValueWithSpaces(arrow.getArrowDirection()));
        buildElementWithValue("arrow-style", arrow.getArrowStyle());
        if (arrow.getArrowhead()) buildElement("arrowhead");
        buildElementWithValue("circular-arrow", arrow.getCircularArrow());
        buildEndElement("arrow");
    }

    private void buildHandbell(Handbell handbell) {
        buildElementWithValueAndAttributes("handbell", BuilderUtil.enumValueWithSpaces(handbell.getValue()), DisplayBuilder.buildDisplay(handbell.getDisplay()));
    }

    private void buildBrassBend(BrassBend brassBend) {
        buildElementWithOptionalAttributes("brass-bend", DisplayBuilder.buildDisplay(brassBend.getDisplay()));
    }

    private void buildFlip(Flip flip) {
        buildElementWithOptionalAttributes("flip", DisplayBuilder.buildDisplay(flip.getDisplay()));
    }

    private void buildSmear(Smear smear) {
        buildElementWithOptionalAttributes("smear", DisplayBuilder.buildDisplay(smear.getDisplay()));
    }

    private void buildOpen(Open open) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(open.getDisplay()));
        attributes.put("smufl", open.getSmufl());
        buildElementWithOptionalAttributes("open", attributes);
    }

    private void buildHalfMuted(HalfMuted halfMuted) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(halfMuted.getDisplay()));
        attributes.put("smufl", halfMuted.getSmufl());
        buildElementWithOptionalAttributes("half-muted", attributes);
    }

    private void buildHarmonMute(HarmonMute harmonMute) {
        buildOpenElement("harmon-mute");
        buildAttributes(DisplayBuilder.buildDisplay(harmonMute.getDisplay()));
        buildCloseElement();
        buildElementWithValueAndAttribute("harmon-closed", BuilderUtil.enumValue(harmonMute.getValue()), "location", BuilderUtil.enumValue(harmonMute.getLocation()));
        buildEndElement("harmon-mute");
    }

    private void buildGolpe(Golpe golpe) {
        buildElementWithOptionalAttributes("golpe", DisplayBuilder.buildDisplay(golpe.getDisplay()));
    }

    private void buildOtherTechnical(OtherTechnical otherTechnical) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(otherTechnical.getDisplay()));
        attributes.put("smufl", otherTechnical.getSmufl());
        buildElementWithValueAndAttributes("other-technical", otherTechnical.getValue(), attributes);
    }

    public static Map<String, String> buildBendSound(BendSound bendSound) {
        Map<String, String> attributes = new HashMap<>();
        if (bendSound ==  null) return attributes;

        attributes.put("accelerate", BuilderUtil.yesOrNo(bendSound.getAccelerate()));
        attributes.put("beats", BuilderUtil.stringValue(bendSound.getBeats()));
        attributes.put("first-beat", BuilderUtil.stringValue(bendSound.getFirstBeat()));
        attributes.put("last-beat", BuilderUtil.stringValue(bendSound.getLastBeat()));

        return attributes;
    }
}
