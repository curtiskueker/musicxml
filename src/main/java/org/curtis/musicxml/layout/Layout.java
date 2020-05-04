package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "layout")
public class Layout extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "page_layout_id")
    private PageLayout pageLayout;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "system_layout_id")
    private SystemLayout systemLayout;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "layout_id", nullable = false)
    @OrderBy("ordering")
    private List<StaffLayout> staffLayouts = new ArrayList<>();

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
