package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.GroupName;
import org.curtis.musicxml.score.GroupSymbol;
import org.curtis.musicxml.score.GroupSymbolType;
import org.curtis.musicxml.score.PartGroup;
import org.curtis.musicxml.score.PartList;
import org.curtis.musicxml.score.PartName;
import org.curtis.musicxml.score.ScorePart;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;

public class PartListHandler extends AbstractHandler {
    private PartList partList;

    public PartListHandler(Element element, PartList partList) {
        super(element);
        this.partList = partList;
    }

    public StringBuilder handle() {
        List<Element> partListSubelements = XmlUtil.getChildElements(getElement());
        PartGroup currentPartGroup = null;
        Boolean hasCurrentPartGroup = false;
        for(Element partListSubelement : partListSubelements) {
            String elementName = partListSubelement.getTagName();
            switch (elementName) {
                case "part-group":
                    String type = partListSubelement.getAttribute("type");
                    if(type.equals("start")) {
                        currentPartGroup = new PartGroup();
                        hasCurrentPartGroup = true;

                        currentPartGroup.setNumber(partListSubelement.getAttribute("number"));

                        GroupName groupName = new GroupName();
                        groupName.setGroupName(XmlUtil.getChildElementText(partListSubelement, "group-name"));
                        currentPartGroup.setGroupName(groupName);

                        GroupName abbreviatedGroupName = new GroupName();
                        abbreviatedGroupName.setGroupName(XmlUtil.getChildElementText(partListSubelement, "group-abbreviation"));
                        currentPartGroup.setGroupAbbreviation(abbreviatedGroupName);

                        GroupSymbol groupSymbol = new GroupSymbol();
                        String groupSymbolTypeValue = XmlUtil.getChildElementText(partListSubelement, "group-symbol");
                        GroupSymbolType groupSymbolType = null;
                        switch (groupSymbolTypeValue) {
                            case "bracket":
                                groupSymbolType = GroupSymbolType.BRACKET;
                        }
                        groupSymbol.setGroupSymbolType(groupSymbolType);
                        currentPartGroup.setGroupSymbol(groupSymbol);
                    } else if(type.equals("stop")) {
                        partList.getPartGroups().add(currentPartGroup);
                        hasCurrentPartGroup = false;
                    }

                    break;
                case "score-part":
                    ScorePart scorePart = new ScorePart();
                    scorePart.setId(partListSubelement.getAttribute("id"));

                    PartName partName = new PartName();
                    partName.setPartName(XmlUtil.getChildElementText(partListSubelement, "part-name"));
                    scorePart.setPartName(partName);

                    PartName partAbbreviation = new PartName();
                    partAbbreviation.setPartName(XmlUtil.getChildElementText(partListSubelement, "part-abbreviation"));
                    scorePart.setPartAbbreviation(partAbbreviation);

                    if(hasCurrentPartGroup) {
                        currentPartGroup.getScoreParts().add(scorePart);
                    }

                    partList.getScoreParts().add(scorePart);

                    break;
            }
        }

        return new StringBuilder();
    }
}
