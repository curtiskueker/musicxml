package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
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
        append(xLinkBuilder.buildElementPosition(link.getElementPosition()));
        buildAttribute("name", link.getName());
        appendLine("</link>");

        return stringBuilder;
    }
}
