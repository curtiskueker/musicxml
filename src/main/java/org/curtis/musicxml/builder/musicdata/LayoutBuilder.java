package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.OutputBuilder;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.PageMargins;
import org.curtis.musicxml.layout.StaffLayout;
import org.curtis.musicxml.layout.SystemLayout;

import java.math.BigDecimal;

public class LayoutBuilder extends OutputBuilder {
    public LayoutBuilder() {

    }

    public static String buildLayout(Layout layout) {
        if (layout == null) return "";

        LayoutBuilder layoutBuilder = new LayoutBuilder();

        PageLayout pageLayout = layout.getPageLayout();
        if (pageLayout != null) {
            layoutBuilder.buildStartElement("page-layout");
            layoutBuilder.buildElementWithValue("page-height", pageLayout.getPageHeight());
            layoutBuilder.buildElementWithValue("page-width", pageLayout.getPageWidth());
            for (PageMargins pageMargins : pageLayout.getPageMargins().values()) {
                layoutBuilder.buildOpenElement("page-margins");
                layoutBuilder.buildAttribute("type", pageMargins.getType());
                layoutBuilder.buildCloseElement();
                layoutBuilder.buildElementWithValue("left-margin", pageMargins.getLeftMargin());
                layoutBuilder.buildElementWithValue("right-margin", pageMargins.getRightMargin());
                layoutBuilder.buildElementWithValue("top-margin", pageMargins.getTopMargin());
                layoutBuilder.buildElementWithValue("bottom-margin", pageMargins.getBottomMargin());
                layoutBuilder.buildEndElement("page-margins");
            }
            layoutBuilder.buildEndElement("page-layout");
        }
        SystemLayout systemLayout = layout.getSystemLayout();
        if (systemLayout != null) {
            layoutBuilder.buildStartElement("system-layout");
            BigDecimal leftMargin = systemLayout.getLeftMargin();
            BigDecimal rightMargin = systemLayout.getRightMargin();
            if (leftMargin != null || rightMargin != null) {
                layoutBuilder.buildStartElement("system-margins");
                layoutBuilder.buildElementWithValue("left-margin", leftMargin);
                layoutBuilder.buildElementWithValue("right-margin", rightMargin);
                layoutBuilder.buildEndElement("system-margins");
            }
            layoutBuilder.buildElementWithValue("system-distance", systemLayout.getSystemDistance());
            layoutBuilder.buildElementWithValue("top-system-distance", systemLayout.getTopSystemDistance());
            if (systemLayout.hasSystemDividers()) {
                layoutBuilder.buildStartElement("system-dividers");
                layoutBuilder.buildElementWithAttributes("left-divider", FormattingBuilder.buildSystemDivider(systemLayout.getLeftSystemDivider()));
                layoutBuilder.buildElementWithAttributes("right-divider", FormattingBuilder.buildSystemDivider(systemLayout.getRightSystemDivider()));
                layoutBuilder.buildEndElement("system-dividers");
            }
            layoutBuilder.buildEndElement("system-layout");
        }
        for (StaffLayout staffLayout : layout.getStaffLayouts()) {
            layoutBuilder.buildOpenElement("staff-layout");
            layoutBuilder.buildAttribute("number", staffLayout.getNumber());
            layoutBuilder.buildCloseElement();
            layoutBuilder.buildElementWithValue("staff-distance", staffLayout.getStaffDistance());
            layoutBuilder.buildEndElement("staff-layout");
        }

        return layoutBuilder.getStringBuilder().toString();
    }
}
