package org.curtis.musicxml.handler;

import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.EditorialVoiceDirection;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.factory.DirectionFactory;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class DirectionHandler extends MusicDataHandler {
    public DirectionHandler() {

    }

    public MusicData handle(Element element) {
        Direction direction = new Direction();

        EditorialVoiceDirection editorialVoiceDirection = new EditorialVoiceDirection();
        editorialVoiceDirection.setFootnote(FormattingFactory.newFormattedText(XmlUtil.getChildElement(element, "footnote")));
        editorialVoiceDirection.setLevel(FormattingFactory.newLevel(XmlUtil.getChildElement(element, "level")));
        editorialVoiceDirection.setVoice(XmlUtil.getChildElementText(element, "voice"));
        direction.setEditorialVoiceDirection(editorialVoiceDirection);
        direction.setPlacement(PlacementFactory.newPlacementLocation(element));
        direction.setDirective(TypeUtil.getYesNo(element.getAttribute("directive")));

        List<Element> directionSubelements = XmlUtil.getChildElements(element);
        for(Element directionSubelement : directionSubelements) {
            String directionElementName = directionSubelement.getTagName();
            switch (directionElementName) {
                case "direction-type":
                    List<DirectionType> directionTypes = direction.getDirectionTypes();
                    List<Element> directionTypeSubelements = XmlUtil.getChildElements(directionSubelement);
                    for(Element directionTypeSubelement : directionTypeSubelements) {
                        DirectionType directionType = DirectionFactory.newDirectionType(directionTypeSubelement);
                        if(directionType != null) {
                            directionTypes.add(directionType);
                            directionType.setDirection(direction);
                        }
                    }
                    break;
                case "offset":
                    direction.setOffset(DirectionFactory.newOffset(directionSubelement));
                    break;
                case "staff":
                    direction.setStaff(StringUtil.getInteger(XmlUtil.getElementText(directionSubelement)));
                    break;
                case "sound":
                    direction.setSound(DirectionFactory.newSound(directionSubelement));
                    break;
            }
        }

        return direction;
    }
}
