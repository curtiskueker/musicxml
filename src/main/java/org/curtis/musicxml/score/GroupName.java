package org.curtis.musicxml.score;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Location;

public class GroupName {
    private String groupName;
    private PrintStyle printStyle;
    private Location justify;

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

    public Location getJustify() {
        return justify;
    }

    public void setJustify(Location justify) {
        this.justify = justify;
    }
}
