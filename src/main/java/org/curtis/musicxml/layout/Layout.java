package org.curtis.musicxml.layout;

import java.util.List;

public class Layout {
    private PageLayout pageLayout;
    private SystemLayout systemLayout;
    private List<StaffLayout> staffLayouts;

    public Layout() {

    }

    public PageLayout getPageLayout() {
        return pageLayout;
    }

    public void setPageLayout(PageLayout pageLayout) {
        this.pageLayout = pageLayout;
    }

    public SystemLayout getSystemLayout() {
        return systemLayout;
    }

    public void setSystemLayout(SystemLayout systemLayout) {
        this.systemLayout = systemLayout;
    }

    public List<StaffLayout> getStaffLayouts() {
        return staffLayouts;
    }

    public void setStaffLayouts(List<StaffLayout> staffLayouts) {
        this.staffLayouts = staffLayouts;
    }
}
