package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.OrnamentFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.OtherOrnament;
import org.curtis.musicxml.note.notation.ornament.Schleifer;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class OrnamentHandler extends AbstractHandler {
    private List<Notation> notationList;

    public OrnamentHandler(List<Notation> notationList) {
        this.notationList = notationList;
    }

    public void handle(Element element) {
        List<Element> ornamentElements = XmlUtil.getChildElements(element);
        Ornaments ornaments = new Ornaments();
        for(Element ornamentElement : ornamentElements) {
            String ornamentElementName = ornamentElement.getTagName();
            Ornament ornament = null;
            switch (ornamentElementName) {
                case "mordent":
                case "inverted-mordent":
                    ornament = OrnamentFactory.newMordent(ornamentElement);
                    break;
                case "trill-mark":
                case "vertical-turn":
                case "shake":
                    ornament = OrnamentFactory.newPlacedTrillSound(ornamentElement);
                    break;
                case "turn":
                case "delayed-turn":
                case "inverted-turn":
                case "delayed-inverted-turn":
                    ornament = OrnamentFactory.newHorizontalTurn(ornamentElement);
                    break;
                case "wavy-line":
                    ornament = OrnamentFactory.newWavyLine(ornamentElement);
                    break;
                case "schleifer":
                    Schleifer schleifer = new Schleifer();
                    schleifer.setPlacement(PlacementFactory.newPlacement(ornamentElement));
                    ornament = schleifer;
                    break;
                case "tremolo":
                    Tremolo tremolo = new Tremolo();
                    tremolo.setTremoloMarks(StringUtil.getInteger(XmlUtil.getElementText(ornamentElement)));
                    tremolo.setType(PlacementUtil.getConnection(ornamentElement.getAttribute("type")));
                    tremolo.setPrintStyle(FormattingFactory.newPrintStyle(ornamentElement));
                    tremolo.setPlacement(PlacementUtil.getLocation(ornamentElement.getAttribute("placement")));
                    ornament = tremolo;
                    break;
                case "other-ornament":
                    OtherOrnament otherOrnament = new OtherOrnament();
                    PlacementText placementText = new PlacementText();
                    placementText.setValue(XmlUtil.getElementText(ornamentElement));
                    placementText.setPrintStyle(FormattingFactory.newPrintStyle(ornamentElement));
                    placementText.setPlacement(PlacementUtil.getLocation(ornamentElement.getAttribute("placement")));
                    otherOrnament.setPlacementText(placementText);
                    ornament = otherOrnament;
                    break;
            }
            if (ornament != null) ornaments.getOrnaments().add(ornament);
        }
        List<Element> accidentalMarkElements = XmlUtil.getChildElements(element, "accidental-mark");
        for (Element accidentalMarkElement : accidentalMarkElements) {
            ornaments.getAccidentalMarks().add(NotationFactory.newAccidentalMark(accidentalMarkElement));
        }
        notationList.add(ornaments);
    }
}
