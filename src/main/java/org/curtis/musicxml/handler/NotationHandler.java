package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Notation;
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
        List<Notation> notationList = notations.getNotations();
        List<Element> notationsSubelements = XmlUtil.getChildElements(element);
        for(Element notationsSubelement : notationsSubelements) {
            switch (notationsSubelement.getTagName()) {
                case "tied":
                    Tied tied = new Tied();
                    tied.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    tied.setLineType(NotationFactory.newLineType(notationsSubelement));
                    tied.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    tied.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                    notationList.add(tied);
                    break;
                case "slur":
                    Slur slur = new Slur();
                    slur.setType(PlacementUtil.getConnection(notationsSubelement.getAttribute("type")));
                    slur.setNumber(StringUtil.getInteger(notationsSubelement.getAttribute("number")));
                    slur.setLineType(NotationFactory.newLineType(notationsSubelement));
                    slur.setDashedFormatting(FormattingFactory.newDashedFormatting(notationsSubelement));
                    slur.setPlacement(PlacementUtil.getLocation(notationsSubelement.getAttribute("placement")));
                    notationList.add(slur);
                    break;
                case "tuplet":
                    Tuplet tuplet = NotationFactory.newTuplet(notationsSubelement);
                    notationList.add(tuplet);
                    break;
                case "ornaments":
                    OrnamentHandler ornamentHandler = new OrnamentHandler(notationList);
                    ornamentHandler.handle(notationsSubelement);
                    break;
                case "articulations":
                    ArticulationHandler articulationHandler = new ArticulationHandler(notationList);
                    articulationHandler.handle(notationsSubelement);
                    break;
                case "fermata":
                    Fermata fermata = NotationFactory.newFermata(notationsSubelement);
                    notationList.add(fermata);
                    break;
            }
        }
        notations.setNotations(notationList);
        notationsList.add(notations);
    }
}
