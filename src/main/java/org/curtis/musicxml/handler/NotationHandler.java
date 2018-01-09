package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.NoteFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.notation.AccidentalMark;
import org.curtis.musicxml.note.notation.Arpeggiate;
import org.curtis.musicxml.note.notation.DynamicsNotation;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Glissando;
import org.curtis.musicxml.note.notation.NonArpeggiate;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.OtherNotation;
import org.curtis.musicxml.note.notation.Slide;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.Tuplet;
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
        notations.setPrintObject(TypeUtil.getYesNo(element.getAttribute("print-object")));

        List<Notation> notationList = notations.getNotations();
        List<Element> notationsSubelements = XmlUtil.getChildElements(element);
        for(Element notationsSubelement : notationsSubelements) {
            switch (notationsSubelement.getTagName()) {
                case "tied":
                    Tied tied = new Tied();
                    tied.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    tied.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    tied.setLineType(NotationFactory.newLineType(notationsSubelement));
                    tied.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    tied.setPosition(PlacementFactory.newPosition(notationsSubelement));
                    tied.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                    tied.setOrientation(PlacementUtil.getLocation(notationsSubelement.getAttribute("orientation")));
                    tied.setBezier(NotationFactory.newBezier(notationsSubelement));
                    tied.setColor(notationsSubelement.getAttribute("color"));
                    notationList.add(tied);
                    break;
                case "slur":
                    Slur slur = new Slur();
                    slur.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    slur.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    slur.setLineType(NotationFactory.newLineType(notationsSubelement));
                    slur.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    slur.setPosition(PlacementFactory.newPosition(notationsSubelement));
                    slur.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                    slur.setOrientation(PlacementUtil.getLocation(notationsSubelement.getAttribute("orientation")));
                    slur.setBezier(NotationFactory.newBezier(notationsSubelement));
                    slur.setColor(notationsSubelement.getAttribute("color"));
                    notationList.add(slur);
                    break;
                case "tuplet":
                    Tuplet tuplet = NotationFactory.newTuplet(notationsSubelement);
                    notationList.add(tuplet);
                    break;
                case "glissando":
                    Glissando glissando = new Glissando();
                    glissando.setValue(XmlUtil.getElementText(notationsSubelement));
                    glissando.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    glissando.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    glissando.setLineType(NotationFactory.newLineType(notationsSubelement));
                    glissando.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    glissando.setPrintStyle(FormattingFactory.newPrintStyle(notationsSubelement));
                    notationList.add(glissando);
                    break;
                case "slide":
                    Slide slide = new Slide();
                    slide.setValue(XmlUtil.getElementText(notationsSubelement));
                    slide.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    slide.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    slide.setLineType(NotationFactory.newLineType(notationsSubelement));
                    slide.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    slide.setPrintStyle(FormattingFactory.newPrintStyle(notationsSubelement));
                    slide.setBendSound(NotationFactory.newBendSound(notationsSubelement));
                    notationList.add(slide);
                    break;
                case "ornaments":
                    OrnamentHandler ornamentHandler = new OrnamentHandler(notationList);
                    ornamentHandler.handle(notationsSubelement);
                    break;
                case "articulations":
                    ArticulationHandler articulationHandler = new ArticulationHandler(notationList);
                    articulationHandler.handle(notationsSubelement);
                    break;
                case "dynamics":
                    DynamicsNotation dynamicsNotation = new DynamicsNotation();
                    dynamicsNotation.setDynamics(NotationFactory.newDynamics(notationsSubelement));
                    notationList.add(dynamicsNotation);
                case "fermata":
                    Fermata fermata = NotationFactory.newFermata(notationsSubelement);
                    notationList.add(fermata);
                    break;
                case "arpeggiate":
                    Arpeggiate arpeggiate = new Arpeggiate();
                    arpeggiate.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    arpeggiate.setDirection(PlacementUtil.getLocation(notationsSubelement.getAttribute("direction")));
                    arpeggiate.setPosition(PlacementFactory.newPosition(notationsSubelement));
                    arpeggiate.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                    arpeggiate.setColor(notationsSubelement.getAttribute("color"));
                    notationList.add(arpeggiate);
                    break;
                case "non-arpeggiate":
                    NonArpeggiate nonArpeggiate = new NonArpeggiate();
                    nonArpeggiate.setType(PlacementUtil.getLocation(notationsSubelement.getAttribute("type")));
                    nonArpeggiate.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    nonArpeggiate.setPosition(PlacementFactory.newPosition(notationsSubelement));
                    nonArpeggiate.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                    nonArpeggiate.setColor(notationsSubelement.getAttribute("color"));
                    notationList.add(nonArpeggiate);
                    break;
                case "accidental-mark":
                    AccidentalMark accidentalMark = new AccidentalMark();
                    accidentalMark.setAccidentalType(NoteFactory.newAccidentalType(notationsSubelement));
                    accidentalMark.setPrintStyle(FormattingFactory.newPrintStyle(notationsSubelement));
                    accidentalMark.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                    notationList.add(accidentalMark);
                    break;
                case "other-notation":
                    OtherNotation otherNotation = new OtherNotation();
                    otherNotation.setValue(XmlUtil.getElementText(notationsSubelement));
                    otherNotation.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    otherNotation.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    otherNotation.setPrintObject(TypeUtil.getYesNo(notationsSubelement.getAttribute("print-object")));
                    otherNotation.setPrintStyle(FormattingFactory.newPrintStyle(notationsSubelement));
                    otherNotation.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                    notationList.add(otherNotation);
                    break;
            }
        }
        notations.setNotations(notationList);
        notationsList.add(notations);
    }
}
