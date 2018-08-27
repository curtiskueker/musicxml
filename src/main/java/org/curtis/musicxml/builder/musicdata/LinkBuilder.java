package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.XLinkBuilder;
import org.curtis.musicxml.link.Link;

public class LinkBuilder extends MusicDataBuilder {
    private Link link;

    public LinkBuilder(Link link) {
        this.link = link;
    }

    public StringBuilder build() {
        append(XLinkBuilder.buildLink(link));

        return stringBuilder;
    }
}
