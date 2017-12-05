package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.OrnamentFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.ornament.AbstractMordent;
import org.curtis.musicxml.note.notation.ornament.HorizontalTurn;
import org.curtis.musicxml.note.notation.ornament.OrnamentAccidentalMark;
import org.curtis.musicxml.note.notation.ornament.OtherOrnament;
import org.curtis.musicxml.note.notation.ornament.PlacedTrillSound;
import org.curtis.musicxml.note.notation.ornament.Schleifer;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
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
        for(Element ornamentElement : ornamentElements) {
            String ornamentElementName = ornamentElement.getTagName();
            switch (ornamentElementName) {
                case "mordent":
                case "inverted-mordent":
                    AbstractMordent abstractMordent = OrnamentFactory.newMordent(ornamentElement);
                    notationList.add(abstractMordent);
                    break;
                case "trill-mark":
                case "vertical-turn":
                case "shake":
                    PlacedTrillSound placedTrillSound = OrnamentFactory.newPlacedTrillSound(ornamentElement);
                    notationList.add(placedTrillSound);
                    break;
                case "turn":
                case "delayed-turn":
                case "inverted-turn":
                case "delayed-inverted-turn":
                    HorizontalTurn horizontalTurn = OrnamentFactory.newHorizontalTurn(ornamentElement);
                    notationList.add(horizontalTurn);
                    break;
                case "wavy-line":
                    WavyLine wavyLine = OrnamentFactory.newWavyLine(ornamentElement);
                    notationList.add(wavyLine);
                    break;
                case "schleifer":
                    Schleifer schleifer = new Schleifer();
                    schleifer.setPlacement(PlacementFactory.newPlacement(ornamentElement));
                    notationList.add(schleifer);
                    break;
                case "tremolo":
                    Tremolo tremolo = new Tremolo();
                    tremolo.setTremoloMarks(StringUtil.getInteger(XmlUtil.getElementText(ornamentElement)));
                    tremolo.setType(PlacementUtil.getConnection(ornamentElement.getAttribute("type")));
                    tremolo.setPrintStyle(FormattingFactory.newPrintStyle(ornamentElement));
                    tremolo.setPlacement(PlacementUtil.getLocation(ornamentElement.getAttribute("placement")));
                    notationList.add(tremolo);
                    break;
                case "other-ornament":
                    OtherOrnament otherOrnament = new OtherOrnament();
                    PlacementText placementText = new PlacementText();
                    placementText.setValue(XmlUtil.getElementText(ornamentElement));
                    placementText.setPrintStyle(FormattingFactory.newPrintStyle(ornamentElement));
                    placementText.setPlacement(PlacementUtil.getLocation(ornamentElement.getAttribute("placement")));
                    otherOrnament.setPlacementText(placementText);
                    notationList.add(otherOrnament);
                    break;
                case "accidental-mark":
                    OrnamentAccidentalMark ornamentAccidentalMark = new OrnamentAccidentalMark();
                    ornamentAccidentalMark.setAccidentalMark(NotationFactory.newAccidentalMark(ornamentElement));
                    notationList.add(ornamentAccidentalMark);
                    break;
            }
        }
    }
}
