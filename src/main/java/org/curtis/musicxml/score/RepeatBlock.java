package org.curtis.musicxml.score;

import org.curtis.musicxml.common.Connection;

public class RepeatBlock {
    private RepeatBlockType repeatBlockType;
    private Connection connectionType;
    private Integer endingCount = 0;
    private Integer endingNumber = 0;

    public RepeatBlock() {

    }

    public RepeatBlockType getRepeatBlockType() {
        return repeatBlockType;
    }

    public void setRepeatBlockType(RepeatBlockType repeatBlockType) {
        this.repeatBlockType = repeatBlockType;
    }

    public Connection getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(Connection connectionType) {
        this.connectionType = connectionType;
    }

    public Integer getEndingCount() {
        return endingCount;
    }

    public void setEndingCount(Integer endingCount) {
        this.endingCount = endingCount;
    }

    public Integer getEndingNumber() {
        return endingNumber;
    }

    public void setEndingNumber(Integer endingNumber) {
        this.endingNumber = endingNumber;
    }
}
