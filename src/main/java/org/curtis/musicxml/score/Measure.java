package org.curtis.musicxml.score;

import java.math.BigDecimal;
import java.util.List;

public class Measure {
    private List<MusicData> musicDataList;
    // TODO: attributes
    private String number;
    // TODO: implicit
    // TODO: non-controlling
    private BigDecimal width;

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

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }
}
