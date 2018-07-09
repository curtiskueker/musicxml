package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.PlacementBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.notation.ornament.AbstractMordent;
import org.curtis.musicxml.note.notation.ornament.DelayedInvertedTurn;
import org.curtis.musicxml.note.notation.ornament.DelayedTurn;
import org.curtis.musicxml.note.notation.ornament.HorizontalTurn;
import org.curtis.musicxml.note.notation.ornament.InvertedMordent;
import org.curtis.musicxml.note.notation.ornament.InvertedTurn;
import org.curtis.musicxml.note.notation.ornament.Mordent;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.OtherOrnament;
import org.curtis.musicxml.note.notation.ornament.PlacedTrillSound;
import org.curtis.musicxml.note.notation.ornament.Schleifer;
import org.curtis.musicxml.note.notation.ornament.Shake;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.TrillSound;
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
        Map<String, String> attributes = new HashMap<>();
        attributes.putAll(FormattingBuilder.buildPrintStyle(placedTrillSound.getPrintStyle()));
        attributes.put("placement", BuilderUtil.enumValue(placedTrillSound.getPlacement()));
        attributes.putAll(buildTrillSound(placedTrillSound.getTrillSound()));

        String elementName;
        if (placedTrillSound instanceof TrillMark) elementName = "trill-mark";
        else if (placedTrillSound instanceof VerticalTurn) elementName = "vertical-turn";
        else if (placedTrillSound instanceof Shake) elementName = "shake";
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

        Map<String, String> attributes = new HashMap<>();
        attributes.putAll(FormattingBuilder.buildPrintStyle(horizontalTurn.getPrintStyle()));
        attributes.put("placement", BuilderUtil.enumValue(horizontalTurn.getPlacement()));
        attributes.putAll(buildTrillSound(horizontalTurn.getTrillSound()));
        attributes.put("slash", BuilderUtil.yesOrNo(horizontalTurn.getSlash()));
        buildElementWithAttributes(elementName, attributes);
    }

    private void buildAbstractMordent(AbstractMordent abstractMordent, Map<String, String> placedTrillSoundAttributes) {
        String elementName;
        if (abstractMordent instanceof Mordent) elementName = "mordent";
        else if (abstractMordent instanceof InvertedMordent) elementName = "inverted-mordent";
        else return;

        Map<String, String> attributes = new HashMap<>();
        attributes.putAll(placedTrillSoundAttributes);
        attributes.put("long", BuilderUtil.yesOrNo(abstractMordent.getLongMordent()));
        attributes.put("approach", BuilderUtil.enumValue(abstractMordent.getApproach()));
        attributes.put("departure", BuilderUtil.enumValue(abstractMordent.getDeparture()));
        buildElementWithAttributes(elementName, attributes);
    }

    private void buildWavyLine(WavyLine wavyLine) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(wavyLine.getType()));
        attributes.put("number", BuilderUtil.stringValue(wavyLine.getNumber()));
        attributes.putAll(PlacementBuilder.buildPosition(wavyLine.getPosition()));
        attributes.put("placement", BuilderUtil.enumValue(wavyLine.getPlacement()));
        attributes.put("color", wavyLine.getColor());
        attributes.putAll(buildTrillSound(wavyLine.getTrillSound()));
        buildElementWithAttributes("wavy-line", attributes);
    }

    private void buildSchleifer(Schleifer schleifer) {
        buildPlacement("schleifer", schleifer.getPlacement());
    }

    private void buildTremolo(Tremolo tremolo) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(tremolo.getType()));
        attributes.putAll(FormattingBuilder.buildPrintStyle(tremolo.getPrintStyle()));
        attributes.put("placement", BuilderUtil.enumValue(tremolo.getPlacement()));
        buildElementWithValueAndAttributes("tremolo", tremolo.getTremoloMarks(), attributes);
    }

    private void buildOtherOrnament(OtherOrnament otherOrnament) {
        buildPlacementText("other-ornament", otherOrnament.getPlacementText());
    }

    public static Map<String, String> buildTrillSound(TrillSound trillSound) {
        Map<String, String> attributes = new HashMap<>();
        if (trillSound == null) return attributes;

        attributes.put("start-note", BuilderUtil.enumValue(trillSound.getStartNote()));
        attributes.put("trill-step", BuilderUtil.enumValue(trillSound.getTrillStep()));
        attributes.put("two-note-turn", BuilderUtil.enumValue(trillSound.getTwoNoteTurn()));
        attributes.put("accelerate", BuilderUtil.yesOrNo(trillSound.getAccelerate()));
        attributes.put("beats", BuilderUtil.stringValue(trillSound.getBeats()));
        attributes.put("second-beat", BuilderUtil.stringValue(trillSound.getSecondBeat()));
        attributes.put("last-beat", BuilderUtil.stringValue(trillSound.getLastBeat()));

        return attributes;
    }
}
