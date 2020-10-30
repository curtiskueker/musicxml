package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.MusicData;
import org.w3c.dom.Element;

public interface MusicDataHandler {
    MusicData handle(Element element);
}
