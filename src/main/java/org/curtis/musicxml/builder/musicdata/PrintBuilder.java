package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.layout.MeasureLayout;

public class PrintBuilder extends BaseBuilder {
    private Print print;

    public PrintBuilder(Print print) {
        this.print = print;
    }

    public StringBuilder build() {
        if (print == null) return stringBuilder;

        append("<print");
        buildAttribute("staff-spacing", BuilderUtil.stringValue(print.getStaffSpacing()));
        buildAttribute("blank-page", print.getBlankPage());
        buildAttribute("page-number", print.getPageNumber());
        appendLine(">");
        append(LayoutBuilder.buildLayout(print.getLayout()));
        MeasureLayout measureLayout = print.getMeasureLayout();
        if (measureLayout != null) {
            appendLine("<measure-layout>");
            buildElementWithValue("measure-distance", BuilderUtil.stringValue(measureLayout.getMeasureDistance()));
            appendLine("</measure-layout>");
        }
        buildElementWithValue("measure-numbering", BuilderUtil.enumValue(print.getMeasureNumberingValue()));
        appendLine("</print>");

        return stringBuilder;
    }
}
