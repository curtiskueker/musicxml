package org.curtis.musicxml.score;

import org.curtis.musicxml.common.PrintStyle;

public class GroupName {
    private String groupName;
    private PrintStyle printStyle;
    private String justify;

    public GroupName() {

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public String getJustify() {
        return justify;
    }

    public void setJustify(String justify) {
        this.justify = justify;
    }
}
