package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.OrnamentFactory;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.OtherOrnament;
import org.curtis.musicxml.note.notation.ornament.Schleifer;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TremoloType;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class OrnamentHandler implements NotationHandler {
    public Notation handle(Element element) {
        List<Element> ornamentElements = XmlUtil.getChildElements(element);
        Ornaments ornaments = new Ornaments();
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
                case "inverted-vertical-turn":
                case "shake":
                case "haydn":
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
                    ornament = new Schleifer();
                    break;
                case "tremolo":
                    Tremolo tremolo = new Tremolo();
                    tremolo.setTremoloMarks(StringUtil.getInteger(XmlUtil.getElementText(ornamentElement)));
                    tremolo.setType(FactoryUtil.enumValue(TremoloType.class, ornamentElement.getAttribute("type")));
                    tremolo.setSmufl(ornamentElement.getAttribute("smufl"));
                    ornament = tremolo;
                    break;
                case "other-ornament":
                    OtherOrnament otherOrnament = new OtherOrnament();
                    otherOrnament.setValue(XmlUtil.getElementText(ornamentElement));
                    otherOrnament.setSmufl(ornamentElement.getAttribute("smufl"));
                    ornament = otherOrnament;
                    break;
            }
            if (ornament != null) {
                ornament.setDisplay(DisplayFactory.newDisplay(ornamentElement));
                ornaments.getOrnaments().add(ornament);
            }
        }
        List<Element> accidentalMarkElements = XmlUtil.getChildElements(element, "accidental-mark");
        for (Element accidentalMarkElement : accidentalMarkElements) ornaments.getAccidentalMarks().add(NotationFactory.newAccidentalMark(accidentalMarkElement));

        return ornaments;
    }
}
