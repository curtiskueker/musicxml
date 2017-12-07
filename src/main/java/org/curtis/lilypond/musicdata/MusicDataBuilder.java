package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.AbstractBuilder;
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
