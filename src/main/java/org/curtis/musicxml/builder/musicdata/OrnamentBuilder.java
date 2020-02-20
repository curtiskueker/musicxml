package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.note.notation.ornament.AbstractMordent;
import org.curtis.musicxml.note.notation.ornament.DelayedInvertedTurn;
import org.curtis.musicxml.note.notation.ornament.DelayedTurn;
import org.curtis.musicxml.note.notation.ornament.Haydn;
import org.curtis.musicxml.note.notation.ornament.HorizontalTurn;
import org.curtis.musicxml.note.notation.ornament.InvertedMordent;
import org.curtis.musicxml.note.notation.ornament.InvertedTurn;
import org.curtis.musicxml.note.notation.ornament.InvertedVerticalTurn;
import org.curtis.musicxml.note.notation.ornament.Mordent;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.OtherOrnament;
import org.curtis.musicxml.note.notation.ornament.PlacedTrillSound;
import org.curtis.musicxml.note.notation.ornament.Schleifer;
import org.curtis.musicxml.note.notation.ornament.Shake;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.Turn;
import org.curtis.musicxml.note.notation.ornament.VerticalTurn;
import org.curtis.musicxml.note.notation.ornament.WavyLine;

import java.util.HashMap;
import java.util.Map;

public class OrnamentBuilder extends MusicDataBuilder {
    private Ornament ornament;

    public OrnamentBuilder(Ornament ornament) {
        this.ornament = ornament;
    }

    public StringBuilder build() {
        if (ornament == null) return stringBuilder;

        if (ornament instanceof PlacedTrillSound) buildPlacedTrillSound((PlacedTrillSound)ornament);
        else if (ornament instanceof HorizontalTurn) buildHorizontalTurn((HorizontalTurn)ornament);
        else if (ornament instanceof WavyLine) buildWavyLine((WavyLine)ornament);
        else if (ornament instanceof Schleifer) buildSchleifer((Schleifer)ornament);
        else if (ornament instanceof Tremolo) buildTremolo((Tremolo)ornament);
        else if (ornament instanceof OtherOrnament) buildOtherOrnament((OtherOrnament)ornament);

        return stringBuilder;
    }

    private void buildPlacedTrillSound(PlacedTrillSound placedTrillSound) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(placedTrillSound.getDisplay()));
        attributes.putAll(buildTrillSound(placedTrillSound.getTrillSound()));

        String elementName;
        if (placedTrillSound instanceof TrillMark) elementName = "trill-mark";
        else if (placedTrillSound instanceof VerticalTurn) elementName = "vertical-turn";
        else if (placedTrillSound instanceof InvertedVerticalTurn) elementName = "inverted-vertical-turn";
        else if (placedTrillSound instanceof Shake) elementName = "shake";
        else if (placedTrillSound instanceof Haydn) elementName = "haydn";
        else if (placedTrillSound instanceof AbstractMordent) {
            buildAbstractMordent((AbstractMordent)placedTrillSound, attributes);
            return;
        }
        else return;

        buildElementWithAttributes(elementName, attributes);
    }

    private void buildHorizontalTurn(HorizontalTurn horizontalTurn) {
        String elementName;
        if (horizontalTurn instanceof Turn) elementName = "turn";
        else if (horizontalTurn instanceof DelayedTurn) elementName = "delayed-turn";
        else if (horizontalTurn instanceof InvertedTurn) elementName = "inverted-turn";
        else if (horizontalTurn instanceof DelayedInvertedTurn) elementName = "delayed-inverted-turn";
        else return;

        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(horizontalTurn.getDisplay()));
        attributes.putAll(buildTrillSound(horizontalTurn.getTrillSound()));
        attributes.put("slash", BuilderUtil.yesOrNo(horizontalTurn.getSlash()));
        buildElementWithAttributes(elementName, attributes);
    }

    private void buildAbstractMordent(AbstractMordent abstractMordent, Map<String, String> placedTrillSoundAttributes) {
        String elementName;
        if (abstractMordent instanceof Mordent) elementName = "mordent";
        else if (abstractMordent instanceof InvertedMordent) elementName = "inverted-mordent";
        else return;

        Map<String, String> attributes = new HashMap<>(placedTrillSoundAttributes);
        attributes.put("long", BuilderUtil.yesOrNo(abstractMordent.getLongMordent()));
        attributes.put("approach", BuilderUtil.enumValue(abstractMordent.getApproach()));
        attributes.put("departure", BuilderUtil.enumValue(abstractMordent.getDeparture()));
        buildElementWithAttributes(elementName, attributes);
    }

    private void buildSchleifer(Schleifer schleifer) {
        buildElementWithAttributes("schleifer", DisplayBuilder.buildDisplay(schleifer.getDisplay()));
    }

    private void buildTremolo(Tremolo tremolo) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(tremolo.getTremoloType()));
        attributes.putAll(DisplayBuilder.buildDisplay(tremolo.getDisplay()));
        attributes.put("smufl", tremolo.getSmufl());
        buildElementWithValueAndAttributes("tremolo", tremolo.getTremoloMarks(), attributes);
    }

    private void buildOtherOrnament(OtherOrnament otherOrnament) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(otherOrnament.getDisplay()));
        attributes.put("smufl", otherOrnament.getSmufl());
        buildElementWithValueAndAttributes("other-ornament", otherOrnament.getValue(), attributes);
    }
}
