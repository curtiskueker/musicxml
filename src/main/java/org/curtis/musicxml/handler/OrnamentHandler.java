package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.OrnamentFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.notation.AccidentalMark;
import org.curtis.musicxml.note.notation.OrnamentAccidental;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.OtherOrnament;
import org.curtis.musicxml.note.notation.ornament.Schleifer;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class OrnamentHandler extends BaseHandler {
    private Ornaments ornaments;

    public OrnamentHandler() {

    }

    public Ornaments getOrnaments() {
        return ornaments;
    }

    public void setOrnaments(Ornaments ornaments) {
        this.ornaments = ornaments;
    }

    public void handle(Element element) {
        List<Element> ornamentElements = XmlUtil.getChildElements(element);
        ornaments = new Ornaments();
        Ornament ornament = null;
        for(Element ornamentElement : ornamentElements) {
            String ornamentElementName = ornamentElement.getTagName();
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
                    schleifer.setPrintPlacement(PlacementFactory.newPlacement(ornamentElement));
                    ornament = schleifer;
                    break;
                case "tremolo":
                    Tremolo tremolo = new Tremolo();
                    tremolo.setTremoloMarks(StringUtil.getInteger(XmlUtil.getElementText(ornamentElement)));
                    tremolo.setType(PlacementUtil.getConnection(ornamentElement.getAttribute("type")));
                    tremolo.setPrintStyle(FormattingFactory.newPrintStyle(ornamentElement));
                    tremolo.setPlacement(PlacementFactory.newPlacementLocation(ornamentElement));
                    ornament = tremolo;
                    break;
                case "other-ornament":
                    OtherOrnament otherOrnament = new OtherOrnament();
                    otherOrnament.setPlacementText(PlacementFactory.newPlacementText(ornamentElement));
                    ornament = otherOrnament;
                    break;
            }
            if (ornament != null) {
                ornaments.getOrnaments().add(ornament);
                ornament.setOrnaments(ornaments);
            }
        }
        List<Element> accidentalMarkElements = XmlUtil.getChildElements(element, "accidental-mark");
        for (Element accidentalMarkElement : accidentalMarkElements) {
            AccidentalMark accidentalMark = NotationFactory.newAccidentalMark(accidentalMarkElement);
            OrnamentAccidental ornamentAccidental = new OrnamentAccidental();
            ornamentAccidental.setAccidentalMark(accidentalMark);
            ornaments.getOrnamentAccidentals().add(ornamentAccidental);
        }
    }
}
