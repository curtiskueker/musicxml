package org.curtis.musicxml.factory;

import org.curtis.musicxml.link.Actuate;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.ElementPosition;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.link.LinkAttributes;
import org.curtis.musicxml.link.Show;
import org.curtis.util.StringUtil;
import org.w3c.dom.Element;

public class LinkFactory {
    private LinkFactory() {

    }

    public static Link newLink(Element element) {
        if (element == null) return null;

        Link link = new Link();
        link.setLinkAttributes(newLinkAttributes(element));
        link.setName(element.getAttribute("name"));
        link.setElementPosition(newElementPosition(element));
        link.setPosition(PlacementFactory.newPosition(element));

        return link;
    }

    public static LinkAttributes newLinkAttributes(Element element) {
        if (element == null) return null;

        LinkAttributes linkAttributes = new LinkAttributes();
        linkAttributes.setHref(element.getAttribute("xlink:href"));
        linkAttributes.setRole(element.getAttribute("xlink:role"));
        linkAttributes.setTitle(element.getAttribute("xlink:title"));
        linkAttributes.setShow((Show)FactoryUtil.enumValue(Show.class, element.getAttribute("xlink:show")));
        String actuate = element.getAttribute("xlink:actuate");
        if (StringUtil.isNotEmpty(actuate)) {
            actuate = actuate.replace("onRequest", "on request");
            actuate = actuate.replace("onLoad", "on load");
            linkAttributes.setActuate((Actuate)FactoryUtil.enumValue(Actuate.class, actuate));
        }

        return linkAttributes;
    }

    public static Bookmark newBookmark(Element element) {
        if (element == null) return null;

        Bookmark bookmark = new Bookmark();
        bookmark.setBookmarkId(element.getAttribute("id"));
        bookmark.setName(element.getAttribute("name"));
        bookmark.setElementPosition(newElementPosition(element));

        return bookmark;
    }

    public static ElementPosition newElementPosition(Element element) {
        if (element == null) return null;

        ElementPosition elementPosition = new ElementPosition();
        elementPosition.setElement(element.getAttribute("element"));
        elementPosition.setPosition(StringUtil.getInteger(element.getAttribute("position")));

        return elementPosition;
    }
}
