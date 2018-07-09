package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.PlacementBuilder;
import org.curtis.musicxml.builder.XLinkBuilder;
import org.curtis.musicxml.link.Link;

public class LinkBuilder extends MusicDataBuilder {
    private Link link;

    public LinkBuilder(Link link) {
        this.link = link;
    }

    public StringBuilder build() {
        buildOpenElement("link");
        buildCloseElement();
        append(XLinkBuilder.buildLinkAttributes(link.getLinkAttributes()));
        buildAttribute("name", link.getName());
        append(XLinkBuilder.buildElementPosition(link.getElementPosition()));
        buildAttributes(PlacementBuilder.buildPosition(link.getPosition()));
        buildEndElement("link");

        return stringBuilder;
    }
}
