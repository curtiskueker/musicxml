package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.XLinkBuilder;
import org.curtis.musicxml.link.Bookmark;

public class BookmarkBuilder extends BaseBuilder {
    private Bookmark bookmark;

    public BookmarkBuilder(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    public StringBuilder build() {
        buildOpenElement("bookmark");
        buildAttribute("id", bookmark.getBookmarkId());
        buildAttribute("name", bookmark.getName());
        XLinkBuilder xLinkBuilder = new XLinkBuilder();
        append(xLinkBuilder.buildElementPosition(bookmark.getElementPosition()));
        buildCloseEmptyElement();

        return stringBuilder;
    }
}
