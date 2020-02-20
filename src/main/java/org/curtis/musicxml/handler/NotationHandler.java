package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.display.Orientation;
import org.curtis.musicxml.display.SymbolDirection;
import org.curtis.musicxml.display.Valign;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.TechnicalFactory;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.notation.Arpeggiate;
import org.curtis.musicxml.note.notation.Glissando;
import org.curtis.musicxml.note.notation.NonArpeggiate;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.OtherNotation;
import org.curtis.musicxml.note.notation.Slide;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.TiedType;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class NotationHandler extends BaseHandler {
    private List<Notations> notationsList;

    public NotationHandler(List<Notations> notationsList) {
        this.notationsList = notationsList;
    }

    public void handle(Element element) {
        Notations notations = new Notations();
        notations.setEditorial(FormattingFactory.newEditorial(element));
        notations.setPrintObject(FormattingFactory.getPrintObject(element));

        List<Notation> notationList = notations.getNotations();
        List<Element> notationsSubelements = XmlUtil.getChildElements(element);
        for(Element notationsSubelement : notationsSubelements) {
            Notation notation = null;
            switch (notationsSubelement.getTagName()) {
                case "tied":
                    Tied tied = new Tied();
                    tied.setTiedType(FactoryUtil.enumValue(TiedType.class, notationsSubelement.getAttribute("type")));
                    tied.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    tied.setLineType(NotationFactory.newLineType(notationsSubelement));
                    tied.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    tied.setOrientation(FactoryUtil.enumValue(Orientation.class, notationsSubelement.getAttribute("orientation")));
                    tied.setBezier(NotationFactory.newBezier(notationsSubelement));
                    notation = tied;
                    break;
                case "slur":
                    Slur slur = new Slur();
                    slur.setConnectionType(FactoryUtil.enumValue(Connection.class, notationsSubelement.getAttribute("type")));
                    slur.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    slur.setLineType(NotationFactory.newLineType(notationsSubelement));
                    slur.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    slur.setOrientation(FactoryUtil.enumValue(Orientation.class, notationsSubelement.getAttribute("orientation")));
                    slur.setBezier(NotationFactory.newBezier(notationsSubelement));
                    notation = slur;
                    break;
                case "tuplet":
                    notation = NotationFactory.newTuplet(notationsSubelement);
                    break;
                case "glissando":
                    Glissando glissando = new Glissando();
                    glissando.setValue(XmlUtil.getElementText(notationsSubelement));
                    glissando.setType(FactoryUtil.enumValue(Connection.class, notationsSubelement.getAttribute("type")));
                    glissando.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    glissando.setLineType(NotationFactory.newLineType(notationsSubelement));
                    glissando.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    notation = glissando;
                    break;
                case "slide":
                    Slide slide = new Slide();
                    slide.setValue(XmlUtil.getElementText(notationsSubelement));
                    slide.setType(FactoryUtil.enumValue(Connection.class, notationsSubelement.getAttribute("type")));
                    slide.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    slide.setLineType(NotationFactory.newLineType(notationsSubelement));
                    slide.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    slide.setBendSound(TechnicalFactory.newBendSound(notationsSubelement));
                    notation = slide;
                    break;
                case "ornaments":
                    OrnamentHandler ornamentHandler = new OrnamentHandler();
                    ornamentHandler.handle(notationsSubelement);
                    notation = ornamentHandler.getOrnaments();
                    break;
                case "technical":
                    TechnicalHandler technicalHandler = new TechnicalHandler();
                    technicalHandler.handle(notationsSubelement);
                    notation = technicalHandler.getTechnicals();
                    break;
                case "articulations":
                    ArticulationHandler articulationHandler = new ArticulationHandler();
                    articulationHandler.handle(notationsSubelement);
                    notation = articulationHandler.getArticulations();
                    break;
                case "dynamics":
                    notation = NotationFactory.newDynamicsNotation(notationsSubelement);
                    break;
                case "fermata":
                    notation = NotationFactory.newFermata(notationsSubelement);
                    break;
                case "arpeggiate":
                    Arpeggiate arpeggiate = new Arpeggiate();
                    arpeggiate.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    arpeggiate.setDirection(FactoryUtil.enumValue(SymbolDirection.class, notationsSubelement.getAttribute("direction")));
                    notation = arpeggiate;
                    break;
                case "non-arpeggiate":
                    NonArpeggiate nonArpeggiate = new NonArpeggiate();
                    nonArpeggiate.setType(FactoryUtil.enumValue(Valign.class, notationsSubelement.getAttribute("type")));
                    nonArpeggiate.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    notation = nonArpeggiate;
                    break;
                case "accidental-mark":
                    notation = NotationFactory.newAccidentalMark(notationsSubelement);
                    break;
                case "other-notation":
                    OtherNotation otherNotation = new OtherNotation();
                    otherNotation.setValue(XmlUtil.getElementText(notationsSubelement));
                    otherNotation.setType(FactoryUtil.enumValue(Connection.class, notationsSubelement.getAttribute("type")));
                    otherNotation.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    otherNotation.setPrintObject(FormattingFactory.getPrintObject(notationsSubelement));
                    otherNotation.setSmufl(notationsSubelement.getAttribute("smufl"));
                    notation = otherNotation;
                    break;
            }
            if (notation != null) {
                notation.setDisplay(DisplayFactory.newDisplay(notationsSubelement));
                notationList.add(notation);
                notation.setNotations(notations);
            }
        }
        notations.setNotations(notationList);
        notationsList.add(notations);
    }
}
