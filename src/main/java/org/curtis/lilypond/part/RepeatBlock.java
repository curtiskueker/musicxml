package org.curtis.lilypond.part;

import org.curtis.musicxml.common.OrderedGroup;

public class RepeatBlock {
    private RepeatBlockType repeatBlockType;
    private OrderedGroup locationInBlock;
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

    public OrderedGroup getLocationInBlock() {
        return locationInBlock;
    }

    public void setLocationInBlock(OrderedGroup locationInBlock) {
        this.locationInBlock = locationInBlock;
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
