package org.curtis.musicxml.factory;

import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.DynamicsMarking;
import org.curtis.musicxml.direction.directiontype.DynamicsType;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.Line;
import org.curtis.musicxml.note.LineShape;
import org.curtis.musicxml.note.LineType;
import org.curtis.musicxml.note.notation.AccidentalMark;
import org.curtis.musicxml.note.notation.Bezier;
import org.curtis.musicxml.note.notation.DynamicsNotation;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.FermataShape;
import org.curtis.musicxml.note.notation.FermataType;
import org.curtis.musicxml.note.notation.ShowTuplet;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.TupletDot;
import org.curtis.musicxml.note.notation.TupletNumber;
import org.curtis.musicxml.note.notation.TupletPortion;
import org.curtis.musicxml.note.notation.TupletType;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;
import java.util.List;

public class NotationFactory {
    private NotationFactory() {

    }

    public static Tuplet newTuplet(Element element) {
        if(element == null) return null;

        Tuplet tuplet = new Tuplet();

        TupletPortion tupletActual = newTupletPortion(XmlUtil.getChildElement(element, "tuplet-actual"));
        tuplet.setTupletActual(tupletActual);
        TupletPortion tupletNormal = newTupletPortion(XmlUtil.getChildElement(element, "tuplet-normal"));
        tuplet.setTupletNormal(tupletNormal);
        tuplet.setType(PlacementUtil.getConnection(element.getAttribute("type")));
        tuplet.setNumber(StringUtil.getInteger(element.getAttribute("number")));
        tuplet.setBracket(TypeUtil.getYesNo(element.getAttribute("bracket")));
        tuplet.setShowNumber(newShowTuplet(element.getAttribute("show-number")));
        tuplet.setShowType(newShowTuplet(element.getAttribute("show-type")));
        tuplet.setLineShape(newLineShape(element));
        tuplet.setPosition(PlacementFactory.newPosition(element));
        tuplet.setPlacement(PlacementFactory.newPlacementLocation(element));

        return tuplet;
    }

    public static TupletPortion newTupletPortion(Element tupletPortionElement) {
        if(tupletPortionElement == null) {
            return null;
        }

        TupletPortion tupletPortion = new TupletPortion();

        Element tupletNumberElement = XmlUtil.getChildElement(tupletPortionElement, "tuplet-number");
        if (tupletNumberElement != null) {
            TupletNumber tupletNumber = new TupletNumber();
            tupletNumber.setValue(StringUtil.getInteger(XmlUtil.getElementText(tupletNumberElement)));
            tupletNumber.setFont(FormattingFactory.newFont(tupletNumberElement));
            tupletNumber.setColor(tupletNumberElement.getAttribute("color"));
            tupletPortion.setTupletNumber(tupletNumber);
        }

        Element tupletTypeElement = XmlUtil.getChildElement(tupletNumberElement, "tuplet-type");
        if (tupletTypeElement != null) {
            TupletType tupletType = new TupletType();
            tupletType.setNoteTypeValue(NoteFactory.newNoteTypeValue(tupletTypeElement));
            tupletType.setFont(FormattingFactory.newFont(tupletTypeElement));
            tupletType.setColor(tupletTypeElement.getAttribute("color"));
            tupletPortion.setTupletType(tupletType);
        }

        List<Element> tupletDotElements = XmlUtil.getChildElements(tupletPortionElement, "tuplet-dots");
        List<TupletDot> tupletDots = tupletPortion.getTupletDots();
        for(Element tupletDotElement : tupletDotElements) {
            TupletDot tupletDot = new TupletDot();
            tupletDot.setFont(FormattingFactory.newFont(tupletDotElement));
            tupletDot.setColor(tupletDotElement.getAttribute("color"));
            tupletDots.add(tupletDot);
        }

        return tupletPortion;
    }

    public static ShowTuplet newShowTuplet(String showTupletValue) {
        if(StringUtil.isEmpty(showTupletValue)) return null;
        switch (showTupletValue) {
            case "actual":
                return ShowTuplet.ACTUAL;
            case "both":
                return ShowTuplet.BOTH;
            case "none":
                return ShowTuplet.NONE;
        }

        return null;
    }

    public static Fermata newFermata(Element fermataElement) {
        if(fermataElement == null) return null;

        Fermata fermata = new Fermata();
        String fermataShape = XmlUtil.getElementText(fermataElement);
        if (StringUtil.isNotEmpty(fermataShape)) {
            switch (fermataShape) {
                case "normal":
                    fermata.setFermataShape(FermataShape.NORMAL);
                    break;
                case "angled":
                    fermata.setFermataShape(FermataShape.ANGLED);
                    break;
                case "square":
                    fermata.setFermataShape(FermataShape.SQUARE);
                    break;
            }
        }

        switch (fermataElement.getAttribute("type")) {
            case "upright":
                fermata.setType(FermataType.UPRIGHT);
                break;
            case "inverted":
                fermata.setType(FermataType.INVERTED);
                break;
        }

        fermata.setPrintStyle(FormattingFactory.newPrintStyle(fermataElement));

        return fermata;
    }

    public static Line newLine(Element element) {
        if (element == null) return null;

        Line line = new Line();
        line.setLineShape(newLineShape(element));
        line.setLineType(newLineType(element));
        line.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
        line.setPrintStyle(FormattingFactory.newPrintStyle(element));
        line.setPlacement(PlacementFactory.newPlacementLocation(element));

        return line;
    }

    public static LineShape newLineShape(Element lineShapeElement) {
        if(lineShapeElement == null) return null;

        String lineShape = lineShapeElement.getAttribute("line-shape");
        if(StringUtil.isEmpty(lineShape)) return null;

        switch (lineShape) {
            case "straight":
                return LineShape.STRAIGHT;
            case "curved":
                return LineShape.CURVED;
            default:
                return null;
        }
    }

    public static LineType newLineType(Element lineTypeElement) {
        if(lineTypeElement == null) return null;

        String lineType = lineTypeElement.getAttribute("line-type");
        if(StringUtil.isEmpty(lineType)) return null;

        switch (lineType) {
            case "solid":
                return LineType.SOLID;
            case "dashed":
                return LineType.DASHED;
            case "dotted":
                return LineType.DOTTED;
            case "wavy":
                return LineType.WAVY;
            default:
                return null;
        }
    }

    public static AccidentalMark newAccidentalMark(Element element) {
        if (element == null) return null;

        AccidentalMark accidentalMark = new AccidentalMark();
        accidentalMark.setAccidentalType(NoteFactory.newAccidentalType(element));
        accidentalMark.setPrintStyle(FormattingFactory.newPrintStyle(element));
        accidentalMark.setPlacement(PlacementFactory.newPlacementLocation(element));

        return accidentalMark;
    }

    public static Dynamics newDynamics(Element element) {
        if(element == null) return null;

        Dynamics dynamics = new Dynamics();

        List<Element> dynamicsElements = XmlUtil.getChildElements(element);
        List<DynamicsMarking> dynamicsMarkings = dynamics.getMarkings();
        for(Element dynamicsElement : dynamicsElements) {
            DynamicsMarking dynamicsMarking = new DynamicsMarking();
            String dynamecsElementName = dynamicsElement.getTagName();
            switch (dynamecsElementName) {
                case "p":
                    dynamicsMarking.setDynamicsType(DynamicsType.P);
                    break;
                case "pp":
                    dynamicsMarking.setDynamicsType(DynamicsType.PP);
                    break;
                case "ppp":
                    dynamicsMarking.setDynamicsType(DynamicsType.PPP);
                    break;
                case "pppp":
                    dynamicsMarking.setDynamicsType(DynamicsType.PPPP);
                    break;
                case "ppppp":
                    dynamicsMarking.setDynamicsType(DynamicsType.PPPPP);
                    break;
                case "pppppp":
                    dynamicsMarking.setDynamicsType(DynamicsType.PPPPPP);
                    break;
                case "f":
                    dynamicsMarking.setDynamicsType(DynamicsType.F);
                    break;
                case "ff":
                    dynamicsMarking.setDynamicsType(DynamicsType.FF);
                    break;
                case "fff":
                    dynamicsMarking.setDynamicsType(DynamicsType.FFF);
                    break;
                case "ffff":
                    dynamicsMarking.setDynamicsType(DynamicsType.FFFF);
                    break;
                case "fffff":
                    dynamicsMarking.setDynamicsType(DynamicsType.FFFFF);
                    break;
                case "ffffff":
                    dynamicsMarking.setDynamicsType(DynamicsType.FFFFFF);
                    break;
                case "mp":
                    dynamicsMarking.setDynamicsType(DynamicsType.MP);
                    break;
                case "mf":
                    dynamicsMarking.setDynamicsType(DynamicsType.MF);
                    break;
                case "sf":
                    dynamicsMarking.setDynamicsType(DynamicsType.SF);
                    break;
                case "sfp":
                    dynamicsMarking.setDynamicsType(DynamicsType.SFP);
                    break;
                case "sfpp":
                    dynamicsMarking.setDynamicsType(DynamicsType.SFPP);
                    break;
                case "fp":
                    dynamicsMarking.setDynamicsType(DynamicsType.FP);
                    break;
                case "rf":
                    dynamicsMarking.setDynamicsType(DynamicsType.RF);
                    break;
                case "rfz":
                    dynamicsMarking.setDynamicsType(DynamicsType.RFZ);
                    break;
                case "sfz":
                    dynamicsMarking.setDynamicsType(DynamicsType.SFZ);
                    break;
                case "sffz":
                    dynamicsMarking.setDynamicsType(DynamicsType.SFFZ);
                    break;
                case "fz":
                    dynamicsMarking.setDynamicsType(DynamicsType.FZ);
                    break;
                case "other-dynamics":
                    dynamicsMarking.setDynamicsType(DynamicsType.OTHER_DYNAMICS);
                    break;
            }
            dynamicsMarkings.add(dynamicsMarking);
        }

        dynamics.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
        dynamics.setPlacement(PlacementFactory.newPlacementLocation(element));
        dynamics.setTextDecoration(LyricFactory.newTextDecoration(element));
        dynamics.setEnclosure(FormattingFactory.newEnclosureShape(element));

        return dynamics;
    }

    public static DynamicsNotation newDynamicsNotation(Element element) {
        if (element == null) return null;

        DynamicsNotation dynamicsNotation = new DynamicsNotation();
        dynamicsNotation.setDynamics(newDynamics(element));

        return dynamicsNotation;
    }

    public static Bezier newBezier(Element element) {
        if (element == null) return null;

        BigDecimal bezierOffset = MathUtil.newBigDecimal(element.getAttribute("bezier-offset"));
        BigDecimal bezierOffset2 = MathUtil.newBigDecimal(element.getAttribute("bezier-offset2"));
        BigDecimal bezierX = MathUtil.newBigDecimal(element.getAttribute("bezier-x"));
        BigDecimal bezierY = MathUtil.newBigDecimal(element.getAttribute("bezier-y"));
        BigDecimal bezierX2 = MathUtil.newBigDecimal(element.getAttribute("bezier-x2"));
        BigDecimal bezierY2 = MathUtil.newBigDecimal(element.getAttribute("bezier-y2"));

        if (bezierOffset == null && bezierOffset2 == null && bezierX == null && bezierY == null && bezierX2 == null && bezierY2 == null) return null;

        Bezier bezier = new Bezier();
        bezier.setBezierOffset(bezierOffset);
        bezier.setBezierOffset2(bezierOffset2);
        bezier.setBezierX(bezierX);
        bezier.setBezierY(bezierY);
        bezier.setBezierX2(bezierX2);
        bezier.setBezierY2(bezierY2);

        return bezier;
    }
}
