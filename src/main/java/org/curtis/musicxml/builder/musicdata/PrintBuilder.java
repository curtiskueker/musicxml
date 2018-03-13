package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.direction.Print;

public class PrintBuilder extends BaseBuilder {
    private Print print;

    public PrintBuilder(Print print) {
        this.print = print;
    }

    public StringBuilder build() {
        if (print == null) return stringBuilder;

        append("<print");
        buildAttribute("blank-page", print.getBlankPage());
        buildAttribute("page-number", print.getPageNumber());
        appendLine(">");
        buildElementWithValue("measure-numbering", BuilderUtil.enumValue(print.getMeasureNumberingValue()));
        appendLine("</print>");

        return stringBuilder;
    }
}
