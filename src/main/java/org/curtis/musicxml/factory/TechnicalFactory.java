package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.musicxml.note.notation.technical.BendSound;
import org.curtis.musicxml.note.notation.technical.Fingering;
import org.curtis.musicxml.note.notation.technical.Fret;
import org.curtis.musicxml.note.notation.technical.HammerOn;
import org.curtis.musicxml.note.notation.technical.HammerOnPullOff;
import org.curtis.musicxml.note.notation.technical.Heel;
import org.curtis.musicxml.note.notation.technical.HeelToe;
import org.curtis.musicxml.note.notation.technical.PullOff;
import org.curtis.musicxml.note.notation.technical.StringNumber;
import org.curtis.musicxml.note.notation.technical.Toe;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class TechnicalFactory {
    private TechnicalFactory() {

    }

    public static Fingering newFingering(Element element) {
        if (element == null) return null;

        Fingering fingering = new Fingering();
        fingering.setValue(XmlUtil.getElementText(element));
        fingering.setSubstitution(TypeUtil.getYesNo(element.getAttribute("substitution")));
        fingering.setAlternate(TypeUtil.getYesNo(element.getAttribute("alternate")));
        fingering.setPrintStyle(FormattingFactory.newPrintStyle(element));
        fingering.setPlacement(PlacementFactory.newPlacementLocation(element));

        return fingering;
    }

    public static Fret newFret(Element element) {
        if (element == null) return null;

        Fret fret = new Fret();
        fret.setValue(StringUtil.getInteger(XmlUtil.getElementText(element)));
        fret.setFont(FormattingFactory.newFont(element));
        fret.setColor(element.getAttribute("color"));

        return fret;
    }

    public static StringNumber newStringNumber(Element element) {
        if (element == null) return null;

        StringNumber stringNumber = new StringNumber();
        stringNumber.setStringNumber(StringUtil.getInteger(XmlUtil.getElementText(element)));
        stringNumber.setPrintStyle(FormattingFactory.newPrintStyle(element));
        stringNumber.setPlacement(PlacementFactory.newPlacementLocation(element));

        return stringNumber;
    }

    public static HammerOn newHammerOn(Element element) {
        if (element == null) return null;

        HammerOn hammerOn = new HammerOn();
        populateHammerOnPullOff(hammerOn, element);

        return hammerOn;
    }

    public static PullOff newPullOff(Element element) {
        if (element == null) return null;

        PullOff pullOff = new PullOff();
        populateHammerOnPullOff(pullOff, element);

        return pullOff;
    }

    private static void populateHammerOnPullOff(HammerOnPullOff hammerOnPullOff, Element element) {
        hammerOnPullOff.setValue(XmlUtil.getElementText(element));
        hammerOnPullOff.setType((Connection) FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
        String numberLevel = element.getAttribute("number");
        if (StringUtil.isNotEmpty(numberLevel)) hammerOnPullOff.setNumber(StringUtil.getInteger(numberLevel));
        hammerOnPullOff.setPrintStyle(FormattingFactory.newPrintStyle(element));
        hammerOnPullOff.setPlacement(PlacementFactory.newPlacementLocation(element));
    }

    public static BendSound newBendSound(Element element) {
        if (element == null) return null;

        BendSound bendSound = new BendSound();
        bendSound.setAccelerate(TypeUtil.getYesNo(element.getAttribute("accelerate")));
        bendSound.setBeats(MathUtil.newBigDecimal(element.getAttribute("beats")));
        bendSound.setFirstBeat(MathUtil.newBigDecimal(element.getAttribute("first-beat")));
        bendSound.setLastBeat(MathUtil.newBigDecimal(element.getAttribute("last-beat")));

        return bendSound;
    }

    public static Heel newHeel(Element element) {
        if (element == null) return null;

        Heel heel = new Heel();
        populateHeelToe(heel, element);

        return heel;
    }

    public static Toe newToe(Element element) {
        if (element == null) return null;

        Toe toe = new Toe();
        populateHeelToe(toe, element);

        return toe;
    }

    private static void populateHeelToe(HeelToe heelToe, Element element) {
        heelToe.setPrintPlacement(PlacementFactory.newPlacement(element));
        heelToe.setSubstitution(TypeUtil.getYesNo(element.getAttribute("substitution")));
    }
}
