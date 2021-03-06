package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "page_layout")
public class PageLayout extends DatabaseItem {
    @Column(name = "page_height", precision = 12, scale = 4)
    private BigDecimal pageHeight;
    @Column(name = "page_width", precision = 12, scale = 4)
    private BigDecimal pageWidth;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "page_layout_id", nullable = false)
    @MapKeyColumn(name = "type")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<MarginType, PageMargins> pageMargins = new HashMap<>();

    public PageLayout() {

    }

    public BigDecimal getPageHeight() {
        return pageHeight;
    }

    public void setPageHeight(BigDecimal pageHeight) {
        this.pageHeight = pageHeight;
    }

    public BigDecimal getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(BigDecimal pageWidth) {
        this.pageWidth = pageWidth;
    }

    public Map<MarginType, PageMargins> getPageMargins() {
        return pageMargins;
    }

    public void setPageMargins(Map<MarginType, PageMargins> pageMargins) {
        this.pageMargins = pageMargins;
    }
}
