package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.AbstractBuilder;
import org.curtis.musicxml.score.MusicData;

public abstract class MusicDataBuilder extends AbstractBuilder {
    private MusicData musicData;

    public MusicDataBuilder(MusicData musicData) {
        this.musicData = musicData;
    }

    public MusicData getMusicData() {
        return musicData;
    }

    public void setMusicData(MusicData musicData) {
        this.musicData = musicData;
    }
}
