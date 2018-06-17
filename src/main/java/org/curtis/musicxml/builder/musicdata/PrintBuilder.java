package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.direction.MeasureNumberingType;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.layout.MeasureLayout;

public class PrintBuilder extends BaseBuilder {
    private Print print;

    public PrintBuilder(Print print) {
        this.print = print;
    }

    public StringBuilder build() {
        if (print == null) return stringBuilder;

        buildOpenElement("print");
        buildAttribute("staff-spacing", BuilderUtil.stringValue(print.getStaffSpacing()));
        buildAttribute("new-system", BuilderUtil.yesOrNo(print.getNewSystem()));
        buildAttribute("new-page", BuilderUtil.yesOrNo(print.getNewPage()));
        buildAttribute("blank-page", print.getBlankPage());
        buildAttribute("page-number", print.getPageNumber());
        buildCloseElement();
        append(LayoutBuilder.buildLayout(print.getLayout()));
        MeasureLayout measureLayout = print.getMeasureLayout();
        if (measureLayout != null) {
            appendLine("<measure-layout>");
            buildElementWithValue("measure-distance", BuilderUtil.stringValue(measureLayout.getMeasureDistance()));
            appendLine("</measure-layout>");
        }
        MeasureNumberingType measureNumberingValue = print.getMeasureNumberingValue();
        if (measureNumberingValue != null) buildElementWithValueAndAttributes("measure-numbering", BuilderUtil.enumValue(measureNumberingValue), FormattingBuilder.buildPrintStyleAlign(print.getPrintStyleAlign()));
        appendLine("</print>");

        return stringBuilder;
    }
}
