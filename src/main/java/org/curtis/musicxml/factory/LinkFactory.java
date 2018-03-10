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
        String show = element.getAttribute("xlink:show");
        if (StringUtil.isNotEmpty(show)) {
            switch (show) {
                case "new":
                    linkAttributes.setShow(Show.NEW);
                    break;
                case "replace":
                    linkAttributes.setShow(Show.REPLACE);
                    break;
                case "embed":
                    linkAttributes.setShow(Show.EMBED);
                    break;
                case "other":
                    linkAttributes.setShow(Show.OTHER);
                    break;
                case "none":
                    linkAttributes.setShow(Show.NONE);
                    break;
            }
        }
        String actuate = element.getAttribute("xlink:actuate");
        if (StringUtil.isNotEmpty(actuate)) {
            switch (actuate) {
                case "onRequest":
                    linkAttributes.setActuate(Actuate.ON_REQUEST);
                    break;
                case "onLoad":
                    linkAttributes.setActuate(Actuate.ON_LOAD);
                    break;
                case "other":
                    linkAttributes.setActuate(Actuate.OTHER);
                    break;
                case "none":
                    linkAttributes.setActuate(Actuate.NONE);
                    break;
            }
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
