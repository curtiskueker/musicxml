package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.link.ElementPosition;
import org.curtis.musicxml.link.LinkAttributes;

public class XLinkBuilder extends OutputBuilder {
    private XLinkBuilder() {

    }

    public static String buildLinkAttributes(LinkAttributes linkAttributes) {
        if (linkAttributes == null) return "";

        XLinkBuilder xLinkBuilder = new XLinkBuilder();
        xLinkBuilder.buildAttribute("xlink:href", linkAttributes.getHref());
        xLinkBuilder.buildAttribute("xlink:type", BuilderUtil.enumValue(linkAttributes.getType()));
        xLinkBuilder.buildAttribute("xlink:role", linkAttributes.getRole());
        xLinkBuilder.buildAttribute("xlink:title", linkAttributes.getTitle());
        xLinkBuilder.buildAttribute("xlink:show", BuilderUtil.enumValue(linkAttributes.getShow()));
        String actuate = BuilderUtil.enumValue(linkAttributes.getActuate());
        actuate = actuate.replace("on-request", "onRequest");
        actuate = actuate.replace("on-load", "onLoad");
        xLinkBuilder.buildAttribute("xlinf:actuate", actuate);

        return xLinkBuilder.stringBuilder.toString();
    }

    public static String buildElementPosition(ElementPosition elementPosition) {
        if (elementPosition == null) return "";

        XLinkBuilder xLinkBuilder = new XLinkBuilder();
        xLinkBuilder.buildAttribute("element", elementPosition.getElement());
        xLinkBuilder.buildAttribute("position", elementPosition.getPosition());

        return xLinkBuilder.stringBuilder.toString();
    }
}
