package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.AbstractBuilder;
import org.curtis.musicxml.score.MusicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MusicDataBuilder extends AbstractBuilder {
    private MusicData musicData;

    public MusicDataBuilder(MusicData musicData) {
        this.musicData = musicData;
    }

    public MusicDataBuilder() {

    }

    public MusicData getMusicData() {
        return musicData;
    }

    public void setMusicData(MusicData musicData) {
        this.musicData = musicData;
    }

    public StringBuilder build() {
        String musidDataClassName = musicData.getClass().getName();
        musidDataClassName = musidDataClassName.replace("org.curtis.musicxml.", "");

        List<String> classNameParts = new ArrayList<>(Arrays.asList(musidDataClassName.split("\\.")));
        String builderSimpleClassName = classNameParts.remove(classNameParts.size() - 1);

        String builderClassName = "org.curtis.lilypond.musicdata.";
        for(String classNamePart : classNameParts) {
            classNamePart = classNamePart.substring(0, 1).toUpperCase() + classNamePart.substring(1);
            builderClassName += classNamePart;
        }

        builderClassName += "Builder";
        String builderMethodName = "build" + builderSimpleClassName;

        try {
            Class builderClass = Class.forName(builderClassName);
            Method builderMethod = builderClass.getMethod(builderMethodName, musicData.getClass());
            return (StringBuilder)builderMethod.invoke(builderClass.newInstance(), musicData);
        } catch (Exception e) {
            // skip
        }

        return stringBuilder;
    }
}
