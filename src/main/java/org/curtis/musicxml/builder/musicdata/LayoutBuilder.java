package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.OutputBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.PageMargins;
import org.curtis.musicxml.layout.StaffLayout;
import org.curtis.musicxml.layout.SystemDividers;
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
            layoutBuilder.appendLine("<page-layout>");
            for (PageMargins pageMargins : pageLayout.getPageMargins().values()) {
                layoutBuilder.append("<page-margins");
                layoutBuilder.buildAttribute("type", BuilderUtil.enumValue(pageMargins.getType()));
                layoutBuilder.appendLine(">");
                // TODO: margins
                layoutBuilder.buildElementWithValue("left-margin", 100);
                layoutBuilder.buildElementWithValue("right-margin", 100);
                layoutBuilder.buildElementWithValue("top-margin", 100);
                layoutBuilder.buildElementWithValue("bottom-margin", 100);
                layoutBuilder.appendLine("</page-margins>");
            }
            layoutBuilder.appendLine("</page-layout>");
        }
        SystemLayout systemLayout = layout.getSystemLayout();
        if (systemLayout != null) {
            layoutBuilder.appendLine("<system-layout>");
            BigDecimal leftMargin = systemLayout.getLeftMargin();
            BigDecimal rightMargin = systemLayout.getRightMargin();
            if (leftMargin != null || rightMargin != null) {
                layoutBuilder.buildElement("system-margins");
            }
            SystemDividers systemDividers = systemLayout.getSystemDividers();
            if (systemDividers != null) {
                layoutBuilder.appendLine("<system-dividers>");
                layoutBuilder.buildElement("left-divider");
                layoutBuilder.buildElement("right-divider");
                layoutBuilder.appendLine("</system-dividers>");
            }
            layoutBuilder.appendLine("</system-layout>");
        }
        for (StaffLayout staffLayout : layout.getStaffLayouts()) {
            layoutBuilder.buildElement("staff-layout");
        }

        return layoutBuilder.getStringBuilder().toString();
    }
}
