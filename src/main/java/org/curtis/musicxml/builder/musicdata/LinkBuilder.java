package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.PlacementBuilder;
import org.curtis.musicxml.builder.XLinkBuilder;
import org.curtis.musicxml.link.Link;

public class LinkBuilder extends BaseBuilder {
    private Link link;

    public LinkBuilder(Link link) {
        this.link = link;
    }

    public StringBuilder build() {
        append("<link");
        appendLine(">");
        XLinkBuilder xLinkBuilder = new XLinkBuilder();
        append(xLinkBuilder.buildLinkAttributes(link.getLinkAttributes()));
        buildAttribute("name", link.getName());
        append(xLinkBuilder.buildElementPosition(link.getElementPosition()));
        PlacementBuilder.buildPosition(link.getPosition()).forEach((k, v) -> buildAttribute(k, v));
        appendLine("</link>");

        return stringBuilder;
    }
}
