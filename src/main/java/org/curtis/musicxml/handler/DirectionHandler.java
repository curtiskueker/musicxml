package org.curtis.musicxml.handler;

import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.DirectionTypeList;
import org.curtis.musicxml.factory.DirectionFactory;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class DirectionHandler implements MusicDataHandler {
    public MusicData handle(Element element) {
        Direction direction = new Direction();

        direction.setElementId(element.getAttribute("id"));
        direction.setEditorial(FormattingFactory.newEditorial(element));
        direction.setVoice(XmlUtil.getChildElementText(element, "voice"));
        direction.setDisplay(DisplayFactory.newDisplay(element));
        direction.setDirective(TypeUtil.getYesNo(element.getAttribute("directive")));

        List<Element> directionSubelements = XmlUtil.getChildElements(element);
        for(Element directionSubelement : directionSubelements) {
            String directionElementName = directionSubelement.getTagName();
            switch (directionElementName) {
                case "direction-type":
                    DirectionTypeList directionTypeList = new DirectionTypeList();
                    directionTypeList.setElementId(directionSubelement.getAttribute("id"));
                    List<DirectionType> directionTypes = directionTypeList.getDirectionTypes();
                    List<Element> directionTypeSubelements = XmlUtil.getChildElements(directionSubelement);
                    for(Element directionTypeSubelement : directionTypeSubelements) {
                        DirectionType directionType = DirectionFactory.newDirectionType(directionTypeSubelement);
                        if(directionType != null) directionTypes.add(directionType);
                    }
                    direction.getDirectionTypeLists().add(directionTypeList);
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
