package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.MusicData;
import org.w3c.dom.Element;

import java.util.List;

public class NoteHandler extends AbstractHandler {
    private List<MusicData> musicDataList;

    public NoteHandler(Element element, List<MusicData> musicDataList) {
        super(element);
        this.musicDataList = musicDataList;
    }

    public void handle() {

    }
}
