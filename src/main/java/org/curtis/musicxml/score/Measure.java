package org.curtis.musicxml.score;

import org.curtis.musicxml.direction.Sound;

import java.util.List;

public class Measure {
    private List<MusicData> musicDataList;
    // TODO: note
    // TODO: backup
    // TODO: forward
    // TODO: attributes
    // TODO: figured-bass
    // TODO: barline
    // TODO: link
    // TODO: bookmark
    private String number;
    // TODO: implicit
    // TODO: non-controlling
    // TODO: width

    public Measure() {

    }

    public List<MusicData> getMusicDataList() {
        return musicDataList;
    }

    public void setMusicDataList(List<MusicData> musicDataList) {
        this.musicDataList = musicDataList;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
