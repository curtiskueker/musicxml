package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.MusicData;
import org.w3c.dom.Element;

public abstract class MusicDataHandler {
    public abstract MusicData handle(Element element);
}
