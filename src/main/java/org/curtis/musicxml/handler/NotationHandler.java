package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.factory.TechnicalFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.notation.Arpeggiate;
import org.curtis.musicxml.note.notation.Glissando;
import org.curtis.musicxml.note.notation.NonArpeggiate;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.OtherNotation;
import org.curtis.musicxml.note.notation.Slide;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class NotationHandler extends AbstractHandler {
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
                    tied.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    tied.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    tied.setLineType(NotationFactory.newLineType(notationsSubelement));
                    tied.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    tied.setPosition(PlacementFactory.newPosition(notationsSubelement));
                    tied.setPlacement(PlacementFactory.newPlacementLocation(notationsSubelement));
                    tied.setOrientation(PlacementUtil.getLocation(notationsSubelement.getAttribute("orientation")));
                    tied.setBezier(NotationFactory.newBezier(notationsSubelement));
                    tied.setColor(notationsSubelement.getAttribute("color"));
                    notation = tied;
                    break;
                case "slur":
                    Slur slur = new Slur();
                    slur.setConnectionType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    slur.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    slur.setLineType(NotationFactory.newLineType(notationsSubelement));
                    slur.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    slur.setPosition(PlacementFactory.newPosition(notationsSubelement));
                    slur.setPlacement(PlacementFactory.newPlacementLocation(notationsSubelement));
                    slur.setOrientation(PlacementUtil.getLocation(notationsSubelement.getAttribute("orientation")));
                    slur.setBezier(NotationFactory.newBezier(notationsSubelement));
                    slur.setColor(notationsSubelement.getAttribute("color"));
                    notation = slur;
                    break;
                case "tuplet":
                    notation = NotationFactory.newTuplet(notationsSubelement);
                    break;
                case "glissando":
                    Glissando glissando = new Glissando();
                    glissando.setValue(XmlUtil.getElementText(notationsSubelement));
                    glissando.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    glissando.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    glissando.setLineType(NotationFactory.newLineType(notationsSubelement));
                    glissando.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    glissando.setPrintStyle(FormattingFactory.newPrintStyle(notationsSubelement));
                    notation = glissando;
                    break;
                case "slide":
                    Slide slide = new Slide();
                    slide.setValue(XmlUtil.getElementText(notationsSubelement));
                    slide.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    slide.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    slide.setLineType(NotationFactory.newLineType(notationsSubelement));
                    slide.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    slide.setPrintStyle(FormattingFactory.newPrintStyle(notationsSubelement));
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
                    arpeggiate.setDirection(PlacementUtil.getLocation(notationsSubelement.getAttribute("direction")));
                    arpeggiate.setPosition(PlacementFactory.newPosition(notationsSubelement));
                    arpeggiate.setPlacement(PlacementFactory.newPlacementLocation(notationsSubelement));
                    arpeggiate.setColor(notationsSubelement.getAttribute("color"));
                    notation = arpeggiate;
                    break;
                case "non-arpeggiate":
                    NonArpeggiate nonArpeggiate = new NonArpeggiate();
                    nonArpeggiate.setType(PlacementUtil.getLocation(notationsSubelement.getAttribute("type")));
                    nonArpeggiate.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    nonArpeggiate.setPosition(PlacementFactory.newPosition(notationsSubelement));
                    nonArpeggiate.setPlacement(PlacementFactory.newPlacementLocation(notationsSubelement));
                    nonArpeggiate.setColor(notationsSubelement.getAttribute("color"));
                    notation = nonArpeggiate;
                    break;
                case "accidental-mark":
                    notation = NotationFactory.newAccidentalMark(notationsSubelement);
                    break;
                case "other-notation":
                    OtherNotation otherNotation = new OtherNotation();
                    otherNotation.setValue(XmlUtil.getElementText(notationsSubelement));
                    otherNotation.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    otherNotation.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    otherNotation.setPrintObject(FormattingFactory.getPrintObject(notationsSubelement));
                    otherNotation.setPrintStyle(FormattingFactory.newPrintStyle(notationsSubelement));
                    otherNotation.setPlacement(PlacementFactory.newPlacementLocation(notationsSubelement));
                    notation = otherNotation;
                    break;
            }
            if (notation != null) {
                notationList.add(notation);
                notation.setNotations(notations);
            }
        }
        notations.setNotations(notationList);
        notationsList.add(notations);
    }
}
