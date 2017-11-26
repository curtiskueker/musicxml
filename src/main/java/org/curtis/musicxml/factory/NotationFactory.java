package org.curtis.musicxml.factory;

import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.LineShape;
import org.curtis.musicxml.note.NoteTypeValue;
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

    public static NoteTypeValue newNoteTypeValue(Element noteTypeElement) {
        if(noteTypeElement == null) {
            return null;
        }

        String noteTypeValue = XmlUtil.getElementText(noteTypeElement);
        if(StringUtil.isEmpty(noteTypeValue)) {
            return null;
        }

        switch (noteTypeValue) {
            case "1024th":
                return NoteTypeValue._1024TH;
            case "512th":
                return NoteTypeValue._512TH;
            case "256th":
                return NoteTypeValue._256TH;
            case "128th":
                return NoteTypeValue._128TH;
            case "64th":
                return NoteTypeValue._64TH;
            case "32nd":
                return NoteTypeValue._32ND;
            case "16th":
                return NoteTypeValue._16TH;
            case "eighth":
                return NoteTypeValue.EIGHTH;
            case "quarter":
                return NoteTypeValue.QUARTER;
            case "half":
                return NoteTypeValue.HALF;
            case "whole":
                return NoteTypeValue.WHOLE;
            case "breve":
                return NoteTypeValue.BREVE;
            case "long":
                return NoteTypeValue.LONG;
            case "maxima":
                return NoteTypeValue.MAXIMA;
        }

        return null;
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
        String lineShape = tupletElement.getAttribute("line-shape");
        if(StringUtil.isNotEmpty(lineShape)) {
            switch (lineShape) {
                case "straight":
                    tuplet.setLineShape(LineShape.STRAIGHT);
                    break;
                case "curved":
                    tuplet.setLineShape(LineShape.CURVED);
                    break;
            }
        }
        tuplet.setPosition(FormattingFactory.newPosition(tupletElement));
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
            tupletType.setNoteTypeValue(newNoteTypeValue(tupletTypeElement));
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
}
