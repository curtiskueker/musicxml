package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.AbstractBuilder;
import org.curtis.lilypond.PartBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.handler.ScoreHandler;
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

    public StringBuilder build() throws BuildException {
        if (musicData == null) return stringBuilder;

        Connection polyphonicVoiceStart = musicData.getPolyphonicVoiceStart();
        if (polyphonicVoiceStart != null) {
            switch (polyphonicVoiceStart) {
                case BEGIN:
                    appendLine("");
                    appendLine("<<");
                    appendLine("{");
                    break;
                case START:
                    appendLine("");
                    appendLine("{");
                    break;
            }
        }

        String musidDataClassName = musicData.getClass().getName();
        musidDataClassName = musidDataClassName.replace("org.curtis.musicxml.", "");

        List<String> classNameParts = new ArrayList<>(Arrays.asList(musidDataClassName.split("\\.")));

        String objectClassName = classNameParts.remove(classNameParts.size() - 1);
        String builderRootClassName = classNameParts.remove(classNameParts.size() - 1);
        builderRootClassName = builderRootClassName.substring(0, 1).toUpperCase() + builderRootClassName.substring(1);
        String builderPackageName = "org.curtis.lilypond.musicdata.";

        String builderClassName = builderPackageName + builderRootClassName + "Builder";
        String builderMethodName = "build" + objectClassName;

        try {
            Class builderClass = Class.forName(builderClassName);
            Method builderMethod = builderClass.getMethod(builderMethodName, musicData.getClass());
            append(builderMethod.invoke(builderClass.newInstance(), musicData).toString());
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof BuildException) {
                // Note exception but continue anyway
                System.err.println(PartBuilder.CURRENT_PART_ID + ": MusicData exception: " + e.getCause().getMessage());
            }
        } catch (Exception e) {
            if (e instanceof NoSuchMethodException) {
                System.err.println("Unimplemented method:" + e.getMessage());
            }
            if (ScoreHandler.DEBUG) {
                e.printStackTrace();
                System.err.println("");
            }
        }

        Connection polyphonicVoiceStop = musicData.getPolyphonicVoiceStop();
        if (polyphonicVoiceStop != null) {
            switch (polyphonicVoiceStop) {
                case STOP:
                    appendLine("");
                    appendLine("}");
                    appendLine("\\\\");
                    break;
                case END:
                    appendLine("");
                    appendLine("}");
                    appendLine(">>");
                    break;
            }
        }

        return stringBuilder;
    }
}
