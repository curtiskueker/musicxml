package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.XLinkBuilder;
import org.curtis.musicxml.link.Bookmark;

public class BookmarkBuilder extends MusicDataBuilder {
    private Bookmark bookmark;

    public BookmarkBuilder(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    public StringBuilder build() {
        append(XLinkBuilder.buildBookmark(bookmark));

        return stringBuilder;
    }
}
