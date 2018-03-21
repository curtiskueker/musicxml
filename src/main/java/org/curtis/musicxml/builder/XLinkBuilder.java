package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.link.ElementPosition;
import org.curtis.musicxml.link.LinkAttributes;

public class XLinkBuilder extends OutputBuilder {
    public XLinkBuilder() {

    }

    public String buildLinkAttributes(LinkAttributes linkAttributes) {
        clear();
        if (linkAttributes == null) return "";

        buildAttribute("xlink:href", linkAttributes.getHref());
        buildAttribute("xlink:type", BuilderUtil.enumValue(linkAttributes.getType()));
        buildAttribute("xlink:role", linkAttributes.getRole());
        buildAttribute("xlink:title", linkAttributes.getTitle());
        buildAttribute("xlink:show", BuilderUtil.enumValue(linkAttributes.getShow()));
        String actuate = BuilderUtil.enumValue(linkAttributes.getActuate());
        actuate = actuate.replace("on-request", "onRequest");
        actuate = actuate.replace("on-load", "onLoad");
        buildAttribute("xlinf:actuate", actuate);

        return stringBuilder.toString();
    }

    public String buildElementPosition(ElementPosition elementPosition) {
        clear();
        if (elementPosition == null) return "";

        buildAttribute("element", elementPosition.getElement());
        buildAttribute("position", elementPosition.getPosition());

        return stringBuilder.toString();
    }
}
