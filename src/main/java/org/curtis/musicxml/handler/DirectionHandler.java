package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Offset;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.type.DirectionType;
import org.curtis.musicxml.direction.type.Dynamics;
import org.curtis.musicxml.direction.type.DynamicsType;
import org.curtis.musicxml.direction.type.Wedge;
import org.curtis.musicxml.direction.type.WedgeType;
import org.curtis.musicxml.direction.type.Words;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class DirectionHandler extends AbstractHandler {
    private List<MusicData> musicDataList;

    public DirectionHandler(Element element, List<MusicData> musicDataList) {
        super(element);
        this.musicDataList = musicDataList;
    }

    public void handle() {
        Direction direction = new Direction();
        direction.setPlacement(PlacementUtil.getLocation(getElement().getAttribute("placement")));
        if(direction.getPlacement() == null) direction.setPlacement(Location.BELOW);
        List<DirectionType> directionTypes = direction.getDirectionTypes();

        List<Element> directionSubelements = XmlUtil.getChildElements(getElement());
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
                                String spread = directionTypeSubelement.getAttribute("spread");
                                if(StringUtil.isNotEmpty(spread)) {
                                    wedge.setSpread(MathUtil.newBigDecimal(spread));
                                }
                                Position wedgePosition = FormattingFactory.newPosition(directionTypeSubelement);
                                wedge.setPosition(wedgePosition);
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

        musicDataList.add(direction);
    }
}
