package org.curtis.musicxml.handler;

import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.factory.DirectionFactory;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class DirectionHandler extends MusicDataHandler {
    public DirectionHandler() {

    }

    public MusicData handle(Element element) {
        Direction direction = new Direction();
        direction.setPlacement(PlacementUtil.getLocation(element.getAttribute("placement")));

        List<Element> directionSubelements = XmlUtil.getChildElements(element);
        for(Element directionSubelement : directionSubelements) {
            String directionElementName = directionSubelement.getTagName();
            switch (directionElementName) {
                case "direction-type":
                    List<DirectionType> directionTypes = direction.getDirectionTypes();
                    List<Element> directionTypeSubelements = XmlUtil.getChildElements(directionSubelement);
                    for(Element directionTypeSubelement : directionTypeSubelements) {
                        DirectionType directionType = DirectionFactory.newDirectionType(directionTypeSubelement);
                        if(directionType != null) directionTypes.add(directionType);
                    }
                    break;
                case "offset":
                    direction.setOffset(DirectionFactory.newOffset(directionSubelement));
                    break;
                case "staff":
                    direction.setStaff(StringUtil.getInteger(XmlUtil.getElementText(directionSubelement)));
                    break;
                case "sound":
                    Sound sound = new Sound();
                    sound.setDynamics(MathUtil.newBigDecimal(directionSubelement.getAttribute("dynamics")));
                    direction.setSound(sound);
                    break;
                case "placement":
                    direction.setPlacement(PlacementUtil.getLocation(directionSubelement.getAttribute("placement")));
                    break;
                case "directive":
                    direction.setDirective(TypeUtil.getYesNo(directionSubelement.getAttribute("directive")));
                    break;
            }
        }

        return direction;
    }
}
