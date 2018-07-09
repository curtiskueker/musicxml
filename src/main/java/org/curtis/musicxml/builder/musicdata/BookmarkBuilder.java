package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.XLinkBuilder;
import org.curtis.musicxml.link.Bookmark;

public class BookmarkBuilder extends MusicDataBuilder {
    private Bookmark bookmark;

    public BookmarkBuilder(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    public StringBuilder build() {
        buildOpenElement("bookmark");
        buildAttribute("id", bookmark.getBookmarkId());
        buildAttribute("name", bookmark.getName());
        append(XLinkBuilder.buildElementPosition(bookmark.getElementPosition()));
        buildCloseEmptyElement();

        return stringBuilder;
    }
}
