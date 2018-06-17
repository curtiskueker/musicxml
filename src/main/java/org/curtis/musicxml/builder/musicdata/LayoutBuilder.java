package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.OutputBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.Margins;
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
            layoutBuilder.buildElementWithValue("page-height", BuilderUtil.stringValue(pageLayout.getPageHeight()));
            layoutBuilder.buildElementWithValue("page-width", BuilderUtil.stringValue(pageLayout.getPageWidth()));
            for (PageMargins pageMargins : pageLayout.getPageMargins().values()) {
                layoutBuilder.buildOpenElement("page-margins");
                layoutBuilder.buildAttribute("type", BuilderUtil.enumValue(pageMargins.getType()));
                layoutBuilder.buildCloseElement();
                Margins margins = pageMargins.getMargins();
                layoutBuilder.buildElementWithValue("left-margin", BuilderUtil.stringValue(margins.getLeftMargin()));
                layoutBuilder.buildElementWithValue("right-margin", BuilderUtil.stringValue(margins.getRightMargin()));
                layoutBuilder.buildElementWithValue("top-margin", BuilderUtil.stringValue(margins.getTopMargin()));
                layoutBuilder.buildElementWithValue("bottom-margin", BuilderUtil.stringValue(margins.getBottomMargin()));
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
                layoutBuilder.appendLine("<system-margins>");
                layoutBuilder.buildElementWithValue("left-margin", BuilderUtil.stringValue(leftMargin));
                layoutBuilder.buildElementWithValue("right-margin", BuilderUtil.stringValue(rightMargin));
                layoutBuilder.appendLine("</system-margins>");
            }
            layoutBuilder.buildElementWithValue("system-distance", BuilderUtil.stringValue(systemLayout.getSystemDistance()));
            layoutBuilder.buildElementWithValue("top-system-distance", BuilderUtil.stringValue(systemLayout.getTopSystemDistance()));
            SystemDividers systemDividers = systemLayout.getSystemDividers();
            if (systemDividers != null) {
                layoutBuilder.appendLine("<system-dividers>");
                layoutBuilder.buildElementWithAttributes("left-divider", FormattingBuilder.buildPrintObjectStyleAlign(systemDividers.getLeftDivider()));
                layoutBuilder.buildElementWithAttributes("right-divider", FormattingBuilder.buildPrintObjectStyleAlign(systemDividers.getRightDivider()));
                layoutBuilder.appendLine("</system-dividers>");
            }
            layoutBuilder.appendLine("</system-layout>");
        }
        for (StaffLayout staffLayout : layout.getStaffLayouts()) {
            layoutBuilder.buildOpenElement("staff-layout");
            layoutBuilder.buildAttribute("number", staffLayout.getNumber());
            layoutBuilder.buildCloseElement();
            layoutBuilder.buildElementWithValue("staff-distance", BuilderUtil.stringValue(staffLayout.getStaffDistance()));
            layoutBuilder.appendLine("</staff-layout>");
        }

        return layoutBuilder.getStringBuilder().toString();
    }
}
