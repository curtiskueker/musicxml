package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.direction.directiontype.Coda;
import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.DynamicsMarking;
import org.curtis.musicxml.direction.directiontype.DynamicsType;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.musicxml.note.Line;
import org.curtis.musicxml.note.LineLength;
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
        tuplet.setType((Connection) FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
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
        return (ShowTuplet)FactoryUtil.enumValue(ShowTuplet.class, showTupletValue);
    }

    public static Fermata newFermata(Element fermataElement) {
        if(fermataElement == null) return null;

        Fermata fermata = new Fermata();
        fermata.setFermataShape((FermataShape)FactoryUtil.enumValueWithEmptyValue(FermataShape.class, XmlUtil.getElementText(fermataElement)));
        fermata.setType((FermataType)FactoryUtil.enumValue(FermataType.class, fermataElement.getAttribute("type")));
        fermata.setPrintStyle(FormattingFactory.newPrintStyle(fermataElement));

        return fermata;
    }

    public static Line newLine(Element element) {
        if (element == null) return null;

        Line line = new Line();
        line.setLineShape(newLineShape(element));
        line.setLineType(newLineType(element));
        line.setLineLength((LineLength)FactoryUtil.enumValue(LineLength.class, element.getAttribute("line-length")));
        line.setDashedFormatting(FormattingFactory.newDashedFormatting(element));
        line.setPrintStyle(FormattingFactory.newPrintStyle(element));
        line.setPlacement(PlacementFactory.newPlacementLocation(element));

        return line;
    }

    private static LineShape newLineShape(Element lineShapeElement) {
        if(lineShapeElement == null) return null;

        return (LineShape)FactoryUtil.enumValue(LineShape.class, lineShapeElement.getAttribute("line-shape"));
    }

    public static LineType newLineType(Element lineTypeElement) {
        if(lineTypeElement == null) return null;

        return (LineType)FactoryUtil.enumValue(LineType.class, lineTypeElement.getAttribute("line-type"));
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
            dynamicsMarking.setDynamicsType((DynamicsType)FactoryUtil.enumValue(DynamicsType.class, dynamicsElement.getTagName()));
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

    public static Coda newCoda(Element element) {
        if (element == null) return null;

        Coda coda = new Coda();
        coda.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
        coda.setSmufl(element.getAttribute("smufl"));
        return coda;
    }
}
