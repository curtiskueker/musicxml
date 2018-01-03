package org.curtis.musicxml.note;

import org.curtis.musicxml.score.MusicData;

import java.util.ArrayList;
import java.util.List;

public class TupletNotes extends MusicData {
    private List<MusicData> musicDataList = new ArrayList<>();
    private String voice;

    public TupletNotes() {

    }

    public List<MusicData> getMusicDataList() {
        return musicDataList;
    }

    public void setMusicDataList(List<MusicData> musicDataList) {
        this.musicDataList = musicDataList;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
