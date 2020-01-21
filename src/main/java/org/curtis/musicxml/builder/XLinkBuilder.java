package org.curtis.musicxml.builder;

import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.ElementPosition;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.link.LinkAttributes;

public class XLinkBuilder extends OutputBuilder {
    private XLinkBuilder() {

    }

    public static String buildLink(Link link) {
        if (link == null) return "";

        XLinkBuilder xLinkBuilder = new XLinkBuilder();
        xLinkBuilder.buildOpenElement("link");
        xLinkBuilder.buildCloseElement();
        xLinkBuilder.append(XLinkBuilder.buildLinkAttributes(link.getLinkAttributes()));
        xLinkBuilder.buildAttribute("name", link.getName());
        xLinkBuilder.append(XLinkBuilder.buildElementPosition(link.getElementPosition()));
        xLinkBuilder.buildAttributes(DisplayBuilder.buildDisplay(link.getDisplay()));
        xLinkBuilder.buildEndElement("link");

        return xLinkBuilder.stringBuilder.toString();
    }

    public static String buildLinkAttributes(LinkAttributes linkAttributes) {
        if (linkAttributes == null) return "";

        XLinkBuilder xLinkBuilder = new XLinkBuilder();
        xLinkBuilder.buildAttribute("xlink:href", BuilderUtil.requiredValue(linkAttributes.getHref()));
        xLinkBuilder.buildAttribute("xlink:type", linkAttributes.getType());
        xLinkBuilder.buildAttribute("xlink:role", linkAttributes.getRole());
        xLinkBuilder.buildAttribute("xlink:title", linkAttributes.getTitle());
        xLinkBuilder.buildAttribute("xlink:show", linkAttributes.getShow());
        String actuate = BuilderUtil.enumValue(linkAttributes.getActuate());
        actuate = actuate.replace("on-request", "onRequest");
        actuate = actuate.replace("on-load", "onLoad");
        xLinkBuilder.buildAttribute("xlink:actuate", actuate);

        return xLinkBuilder.stringBuilder.toString();
    }

    public static String buildBookmark(Bookmark bookmark) {
        if (bookmark == null) return "";

        XLinkBuilder xLinkBuilder = new XLinkBuilder();
        xLinkBuilder.buildOpenElement("bookmark");
        xLinkBuilder.buildAttribute("id", bookmark.getBookmarkId());
        xLinkBuilder.buildAttribute("name", bookmark.getName());
        xLinkBuilder.append(XLinkBuilder.buildElementPosition(bookmark.getElementPosition()));
        xLinkBuilder.buildCloseEmptyElement();

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
