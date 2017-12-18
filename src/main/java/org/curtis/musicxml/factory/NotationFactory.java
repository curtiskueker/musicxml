package org.curtis.musicxml.factory;

import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.DynamicsType;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.Line;
import org.curtis.musicxml.note.LineShape;
import org.curtis.musicxml.note.LineType;
import org.curtis.musicxml.note.notation.AccidentalMark;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.FermataShape;
import org.curtis.musicxml.note.notation.FermataType;
import org.curtis.musicxml.note.notation.ShowTuplet;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.TupletDot;
import org.curtis.musicxml.note.notation.TupletNumber;
import org.curtis.musicxml.note.notation.TupletPortion;
import org.curtis.musicxml.note.notation.TupletType;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class NotationFactory {
    private NotationFactory() {

    }

    public static Tuplet newTuplet(Element tupletElement) {
        if(tupletElement == null) {
            return null;
        }

        Tuplet tuplet = new Tuplet();

        TupletPortion tupletActual = newTupletPortion(XmlUtil.getChildElement(tupletElement, "tuplet-actual"));
        tuplet.setTupletActual(tupletActual);
        TupletPortion tupletNormal = newTupletPortion(XmlUtil.getChildElement(tupletElement, "tuplet-normal"));
        tuplet.setTupletNormal(tupletNormal);
        tuplet.setType(PlacementUtil.getConnection(tupletElement.getAttribute("type")));
        tuplet.setNumber(StringUtil.getInteger(tupletElement.getAttribute("number")));
        tuplet.setBracket(TypeUtil.getYesNo(tupletElement.getAttribute("bracket")));
        tuplet.setShowNumber(newShowTuplet(tupletElement.getAttribute("show-number")));
        tuplet.setShowType(newShowTuplet(tupletElement.getAttribute("show-type")));
        tuplet.setLineShape(newLineShape(tupletElement));
        tuplet.setPosition(PlacementFactory.newPosition(tupletElement));
        tuplet.setPlacement(PlacementUtil.getLocation(tupletElement.getAttribute("placement")));

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
        switch (fermataShape) {
            case "normal":
            case "":
                fermata.setFermataShape(FermataShape.NORMAL);
                break;
            case "angled":
                fermata.setFermataShape(FermataShape.ANGLED);
                break;
            case "square":
                fermata.setFermataShape(FermataShape.SQUARE);
                break;
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

    public static Line newLine(Element lineElement) {
        Line line = new Line();
        line.setLineShape(newLineShape(lineElement));
        line.setLineType(newLineType(lineElement));
        line.setDashedFormatting(FormattingFactory.newDashedFormatting(lineElement));
        line.setPrintStyle(FormattingFactory.newPrintStyle(lineElement));
        line.setPlacement(PlacementUtil.getLocation(lineElement.getAttribute("placement")));

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

    public static AccidentalMark newAccidentalMark(Element accidentalMarkElement) {
        if (accidentalMarkElement == null) return null;

        AccidentalMark accidentalMark = new AccidentalMark();
        accidentalMark.setAccidentalType(NoteFactory.newAccidentalType(accidentalMarkElement));
        accidentalMark.setPrintStyle(FormattingFactory.newPrintStyle(accidentalMarkElement));
        accidentalMark.setPlacement(PlacementUtil.getLocation(accidentalMarkElement.getAttribute("placement")));

        return accidentalMark;
    }

    public static Dynamics newDynamics(Element element) {
        if(element == null) return null;

        Dynamics dynamics = new Dynamics();

        List<Element> dynamicsElements = XmlUtil.getChildElements(element);
        List<DynamicsType> dynamicsTypes = dynamics.getTypes();
        for(Element dynamicsElement : dynamicsElements) {
            String dynamecsElementName = dynamicsElement.getTagName();
            switch (dynamecsElementName) {
                case "p":
                    dynamicsTypes.add(DynamicsType.P);
                    break;
                case "pp":
                    dynamicsTypes.add(DynamicsType.PP);
                    break;
                case "ppp":
                    dynamicsTypes.add(DynamicsType.PPP);
                    break;
                case "pppp":
                    dynamicsTypes.add(DynamicsType.PPPP);
                    break;
                case "ppppp":
                    dynamicsTypes.add(DynamicsType.PPPPP);
                    break;
                case "pppppp":
                    dynamicsTypes.add(DynamicsType.PPPPPP);
                    break;
                case "f":
                    dynamicsTypes.add(DynamicsType.F);
                    break;
                case "ff":
                    dynamicsTypes.add(DynamicsType.FF);
                    break;
                case "fff":
                    dynamicsTypes.add(DynamicsType.FFF);
                    break;
                case "ffff":
                    dynamicsTypes.add(DynamicsType.FFFF);
                    break;
                case "fffff":
                    dynamicsTypes.add(DynamicsType.FFFFF);
                    break;
                case "ffffff":
                    dynamicsTypes.add(DynamicsType.FFFFFF);
                    break;
                case "mp":
                    dynamicsTypes.add(DynamicsType.MP);
                    break;
                case "mf":
                    dynamicsTypes.add(DynamicsType.MF);
                    break;
                case "sf":
                    dynamicsTypes.add(DynamicsType.SF);
                    break;
                case "sfp":
                    dynamicsTypes.add(DynamicsType.SFP);
                    break;
                case "sfpp":
                    dynamicsTypes.add(DynamicsType.SFPP);
                    break;
                case "fp":
                    dynamicsTypes.add(DynamicsType.FP);
                    break;
                case "rf":
                    dynamicsTypes.add(DynamicsType.RF);
                    break;
                case "rfz":
                    dynamicsTypes.add(DynamicsType.RFZ);
                    break;
                case "sfz":
                    dynamicsTypes.add(DynamicsType.SFZ);
                    break;
                case "sffz":
                    dynamicsTypes.add(DynamicsType.SFFZ);
                    break;
                case "fz":
                    dynamicsTypes.add(DynamicsType.FZ);
                    break;
                case "other-dynamics":
                    dynamicsTypes.add(DynamicsType.OTHER_DYNAMICS);
                    break;
            }
        }

        dynamics.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(element));
        dynamics.setPlacement(PlacementUtil.getLocation(element.getAttribute("placement")));
        dynamics.setTextDecoration(LyricFactory.newTextDecoration(element));
        dynamics.setEnclosure(FormattingFactory.newEnclosureShape(element));

        return dynamics;
    }
}
