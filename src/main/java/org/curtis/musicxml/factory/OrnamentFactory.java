package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.display.Placement;
import org.curtis.musicxml.note.notation.ornament.Haydn;
import org.curtis.musicxml.note.notation.ornament.InvertedVerticalTurn;
import org.curtis.musicxml.util.TypeUtil;
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
            case "inverted-vertical-turn":
                placedTrillSound = new InvertedVerticalTurn();
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
            case "haydn":
                placedTrillSound = new Haydn();
                break;
            default:
                return null;
        }

        placedTrillSound.setDisplay(DisplayFactory.newDisplay(element));
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
        abstractMordent.setApproach(FactoryUtil.enumValue(Placement.class, element.getAttribute("approach")));
        abstractMordent.setDeparture(FactoryUtil.enumValue(Placement.class, element.getAttribute("departure")));

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

        horizontalTurn.setDisplay(DisplayFactory.newDisplay(element));
        horizontalTurn.setTrillSound(newTrillSound(element));
        horizontalTurn.setSlash(TypeUtil.getYesNo(element.getAttribute("slash")));

        return horizontalTurn;
    }

    public static WavyLine newWavyLine(Element element) {
        if(element == null) return null;

        WavyLine wavyLine = new WavyLine();
        wavyLine.setType(FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
        wavyLine.setNumber(StringUtil.getInteger(element.getAttribute("number")));
        wavyLine.setDisplay(DisplayFactory.newDisplay(element));
        wavyLine.setTrillSound(OrnamentFactory.newTrillSound(element));

        return wavyLine;
    }

    private static TrillSound newTrillSound(Element element) {
        if(element == null) return null;

        TrillSound trillSound = new TrillSound();
        trillSound.setStartNote(FactoryUtil.enumValue(StartNote.class, element.getAttribute("start-note")));
        trillSound.setTrillStep(FactoryUtil.enumValue(TrillStep.class, element.getAttribute("trill-step")));
        trillSound.setTwoNoteTurn(FactoryUtil.enumValue(TwoNoteTurn.class, element.getAttribute("two-note-turn")));
        trillSound.setAccelerate(TypeUtil.getYesNo(element.getAttribute("accelerate")));
        trillSound.setBeats(MathUtil.newBigDecimal(element.getAttribute("beats")));
        trillSound.setSecondBeat(MathUtil.newBigDecimal(element.getAttribute("second-beat")));
        trillSound.setLastBeat(MathUtil.newBigDecimal(element.getAttribute("last-beat")));

        return trillSound;
    }
}
