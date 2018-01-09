package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.NameDisplay;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.score.GroupName;
import org.curtis.musicxml.score.PartName;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class ScorePartFactory {
    private ScorePartFactory() {

    }

    public static GroupName newGroupName(Element element) {
        if (element == null) return null;

        GroupName groupName = new GroupName();
        groupName.setGroupName(XmlUtil.getChildElementText(element, "group-name"));
        groupName.setPrintStyle(FormattingFactory.newPrintStyle(element));
        groupName.setJustify(PlacementUtil.getLocation(element.getAttribute("justify")));

        return groupName;
    }

    public static NameDisplay newNameDisplay(Element element) {
        if (element == null) return null;

        NameDisplay nameDisplay = new NameDisplay();
        for(Element subelement : XmlUtil.getChildElements(element)) {
            String subelementName = subelement.getTagName();
            switch (subelementName) {
                case "display-text":
                    nameDisplay.getDisplayTextList().add(FormattingFactory.newFormattedText(subelement));
                    break;
                case "accidental-text":
                    nameDisplay.getAccidentalTextList().add(NoteFactory.newAccidentalText(subelement));
                    break;
            }
        }

        return nameDisplay;
    }

    public static PartName newPartName(Element element) {
        if (element == null) return null;

        PartName partName = new PartName();
        partName.setPartName(XmlUtil.getElementText(element));
        partName.setPartNamePrintStyle(FormattingFactory.newPrintStyle(element));
        partName.setPartNamePrintObject(TypeUtil.getYesNo(element.getAttribute("print-object")));
        partName.setPartNameJustify(PlacementUtil.getLocation(element.getAttribute("justify")));

        return partName;
    }
}
