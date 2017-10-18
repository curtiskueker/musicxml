package org.curtis.musicxml.builder;

import org.curtis.util.StringUtil;

public class ScoreHeaderBuilder extends AbstractBuilder {
    private String movementTitle;
    private String composer;

    public ScoreHeaderBuilder() {

    }

    public String getMovementTitle() {
        return movementTitle;
    }

    public void setMovementTitle(String movementTitle) {
        this.movementTitle = movementTitle;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public StringBuilder build() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\\header {\n");

        if(StringUtil.isNotEmpty(movementTitle)) {
            stringBuilder.append("title = \"");
            stringBuilder.append(movementTitle);
            stringBuilder.append("\"\n");
        }
        if(StringUtil.isNotEmpty(composer)) {
            stringBuilder.append("composer = \"");
            stringBuilder.append(composer);
            stringBuilder.append("\"\n");
        }

        stringBuilder.append("}\n");

        return stringBuilder;
    }
}
