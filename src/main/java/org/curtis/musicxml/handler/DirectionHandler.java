package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.DynamicsType;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.WedgeType;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.NotationFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class DirectionHandler extends MusicDataHandler {
    public DirectionHandler() {

    }

    public MusicData handle(Element element) {
        Direction direction = new Direction();
        direction.setPlacement(PlacementUtil.getLocation(element.getAttribute("placement")));
        List<DirectionType> directionTypes = direction.getDirectionTypes();

        List<Element> directionSubelements = XmlUtil.getChildElements(element);
        for(Element directionSubelement : directionSubelements) {
            String directionElementName = directionSubelement.getTagName();
            switch (directionElementName) {
                case "direction-type":
                    List<Element> directionTypeSubelements = XmlUtil.getChildElements(directionSubelement);
                    for(Element directionTypeSubelement : directionTypeSubelements) {
                        String directionTypeElementName = directionTypeSubelement.getTagName();
                        switch (directionTypeElementName) {
                            case "words":
                                Words words = new Words();
                                FormattedText formattedText = FormattingFactory.newFormattedText(directionTypeSubelement);
                                words.setFormattedText(formattedText);
                                directionTypes.add(words);
                                break;
                            case "wedge":
                                Wedge wedge = new Wedge();
                                String wedgeType = directionTypeSubelement.getAttribute("type");
                                switch (wedgeType) {
                                    case "crescendo":
                                        wedge.setType(WedgeType.CRESCENDO);
                                        break;
                                    case "diminuendo":
                                        wedge.setType(WedgeType.DIMINUENDO);
                                        break;
                                    case "stop":
                                        wedge.setType(WedgeType.STOP);
                                        break;
                                    case "continue":
                                        wedge.setType(WedgeType.CONTINUE);
                                        break;
                                }
                                wedge.setSpread(MathUtil.newBigDecimal(directionTypeSubelement.getAttribute("spread")));
                                wedge.setLineType(NotationFactory.newLineType(directionTypeSubelement));
                                wedge.setDashedFormatting(FormattingFactory.newDashedFormatting(directionTypeSubelement));
                                wedge.setPosition(PlacementFactory.newPosition(directionTypeSubelement));
                                directionTypes.add(wedge);
                                break;
                            case "dynamics":
                                Dynamics dynamics = new Dynamics();

                                PrintStyleAlign dynamicsPrintStyleAlign = FormattingFactory.newPrintStyleAlign(directionTypeSubelement);
                                dynamics.setPrintStyleAlign(dynamicsPrintStyleAlign);

                                List<Element> dynamicsElements = XmlUtil.getChildElements(directionTypeSubelement);
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
                                        case "f":
                                            dynamicsTypes.add(DynamicsType.F);
                                            break;
                                        case "ff":
                                            dynamicsTypes.add(DynamicsType.FF);
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
                                        case "fp":
                                            dynamicsTypes.add(DynamicsType.FP);
                                            break;
                                        case "fz":
                                            dynamicsTypes.add(DynamicsType.FZ);
                                            break;
                                    }
                                }
                                directionTypes.add(dynamics);
                                break;
                        }
                    }
                    break;
                case "offset":
                    Offset offset = new Offset();
                    offset.setDivisions(MathUtil.newBigDecimal(XmlUtil.getElementText(directionSubelement)));
                    direction.setOffset(offset);
                    break;
                case "sound":
                    Sound sound = new Sound();
                    sound.setDynamics(MathUtil.newBigDecimal(directionSubelement.getAttribute("dynamics")));
                    direction.setSound(sound);
                    break;
            }
        }

        return direction;
    }
}
