package org.curtis.musicxml.factory;

import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.notation.ornament.AbstractMordent;
import org.curtis.musicxml.note.notation.ornament.DelayedInvertedTurn;
import org.curtis.musicxml.note.notation.ornament.DelayedTurn;
import org.curtis.musicxml.note.notation.ornament.HorizontalTurn;
import org.curtis.musicxml.note.notation.ornament.InvertedMordent;
import org.curtis.musicxml.note.notation.ornament.InvertedTurn;
import org.curtis.musicxml.note.notation.ornament.Mordent;
import org.curtis.musicxml.note.notation.ornament.PlacedTrillSound;
import org.curtis.musicxml.note.notation.ornament.Shake;
import org.curtis.musicxml.note.notation.ornament.StartNote;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.TrillSound;
import org.curtis.musicxml.note.notation.ornament.TrillStep;
import org.curtis.musicxml.note.notation.ornament.Turn;
import org.curtis.musicxml.note.notation.ornament.TwoNoteTurn;
import org.curtis.musicxml.note.notation.ornament.VerticalTurn;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.w3c.dom.Element;

public class OrnamentFactory {
    private OrnamentFactory() {

    }

    public static TrillSound newTrillSound(Element element) {
        if(element == null) return null;

        TrillSound trillSound = new TrillSound();

        String startNote = element.getAttribute("start-note");
        if(StringUtil.isNotEmpty(startNote)) {
            switch (startNote) {
                case "upper":
                    trillSound.setStartNote(StartNote.UPPER);
                    break;
                case "main":
                    trillSound.setStartNote(StartNote.MAIN);
                    break;
                case "below":
                    trillSound.setStartNote(StartNote.BELOW);
                    break;
            }
        }

        String trillStep = element.getAttribute("trill-step");
        if(StringUtil.isNotEmpty(trillStep)) {
            switch (trillStep) {
                case "whole":
                    trillSound.setTrillStep(TrillStep.WHOLE);
                    break;
                case "half":
                    trillSound.setTrillStep(TrillStep.HALF);
                    break;
                case "unison":
                    trillSound.setTrillStep(TrillStep.UNISON);
                    break;
            }
        }

        String twoNoteTurn = element.getAttribute("two-note-turn");
        if(StringUtil.isNotEmpty(twoNoteTurn)) {
            switch (twoNoteTurn) {
                case "whole":
                    trillSound.setTwoNoteTurn(TwoNoteTurn.WHOLE);
                    break;
                case "half":
                    trillSound.setTwoNoteTurn(TwoNoteTurn.HALF);
                    break;
                case "none":
                    trillSound.setTwoNoteTurn(TwoNoteTurn.NONE);
                    break;
            }
        }

        trillSound.setAccelerate(TypeUtil.getYesNo(element.getAttribute("accelerate")));
        trillSound.setBeats(MathUtil.newBigDecimal(element.getAttribute("beats")));
        trillSound.setSecondBeat(MathUtil.newBigDecimal(element.getAttribute("second-beat")));
        trillSound.setLastBeat(MathUtil.newBigDecimal(element.getAttribute("last-beat")));

        return trillSound;
    }

    public static PlacedTrillSound newPlacedTrillSound(Element element) {
        if(element == null) return null;

        PlacedTrillSound placedTrillSound;
        String elementName = element.getTagName();
        switch (elementName) {
            case "trill-mark":
                placedTrillSound = new TrillMark();
                break;
            case "vertical-turn":
                placedTrillSound = new VerticalTurn();
                break;
            case "shake":
                placedTrillSound = new Shake();
                break;
            case "mordent":
                placedTrillSound = new Mordent();
                break;
            case "inverted-mordent":
                placedTrillSound = new InvertedMordent();
                break;
            default:
                return null;
        }

        placedTrillSound.setPrintStyle(FormattingFactory.newPrintStyle(element));
        placedTrillSound.setPlacement(PlacementUtil.getLocation(element.getAttribute("placement")));
        placedTrillSound.setTrillSound(newTrillSound(element));

        return placedTrillSound;
    }

    public static AbstractMordent newMordent(Element element) {
        if(element == null) return null;

        PlacedTrillSound placedTrillSound = newPlacedTrillSound(element);
        if(!(placedTrillSound instanceof AbstractMordent)) {
            return null;
        }

        AbstractMordent abstractMordent = (AbstractMordent)placedTrillSound;
        abstractMordent.setLongMordent(TypeUtil.getYesNo(element.getAttribute("long")));
        abstractMordent.setApproach(PlacementUtil.getLocation(element.getAttribute("approach")));
        abstractMordent.setDeparture(PlacementUtil.getLocation(element.getAttribute("departure")));

        return abstractMordent;
    }

    public static HorizontalTurn newHorizontalTurn(Element element) {
        if(element == null) return null;

        HorizontalTurn horizontalTurn;
        String elementName = element.getTagName();
        switch (elementName) {
            case "turn":
                horizontalTurn = new Turn();
                break;
            case "delayed-turn":
                horizontalTurn = new DelayedTurn();
                break;
            case "inverted-turn":
                horizontalTurn = new InvertedTurn();
                break;
            case "delayed-inverted-turn":
                horizontalTurn = new DelayedInvertedTurn();
                break;
            default:
                return null;
        }

        horizontalTurn.setPrintStyle(FormattingFactory.newPrintStyle(element));
        horizontalTurn.setPlacement(PlacementUtil.getLocation(element.getAttribute("placement")));
        horizontalTurn.setTrillSound(newTrillSound(element));
        horizontalTurn.setSlash(TypeUtil.getYesNo(element.getAttribute("slash")));

        return horizontalTurn;
    }

    public static WavyLine newWavyLine(Element element) {
        if(element == null) return null;

        WavyLine wavyLine = new WavyLine();
        wavyLine.setType(PlacementUtil.getConnection(element.getAttribute("type")));
        wavyLine.setNumber(StringUtil.getInteger(element.getAttribute("number")));
        wavyLine.setPosition(FormattingFactory.newPosition(element));
        wavyLine.setPlacement(PlacementUtil.getLocation(element.getAttribute("placement")));
        wavyLine.setColor(element.getAttribute("color"));
        wavyLine.setTrillSound(OrnamentFactory.newTrillSound(element));

        return wavyLine;
    }
}
