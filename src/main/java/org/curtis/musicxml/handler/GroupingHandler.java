package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.direction.Feature;
import org.curtis.musicxml.direction.Grouping;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class GroupingHandler extends MusicDataHandler {
    public GroupingHandler() {

    }

    public MusicData handle(Element element) {
        Grouping grouping = new Grouping();
        grouping.setElementId(element.getAttribute("id"));

        List<Element> featureElements = XmlUtil.getChildElements(element, "feature");
        for (Element featureElement : featureElements) {
            Feature feature = new Feature();
            feature.setValue(XmlUtil.getElementText(featureElement));
            feature.setType(featureElement.getAttribute("type"));
            grouping.getFeatures().add(feature);
        }
        grouping.setType(FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
        String groupingNumber = element.getAttribute("number");
        if (StringUtil.isNotEmpty(groupingNumber)) grouping.setNumber(groupingNumber);
        grouping.setNumberOf(element.getAttribute("number-of"));

        return grouping;
    }
}
