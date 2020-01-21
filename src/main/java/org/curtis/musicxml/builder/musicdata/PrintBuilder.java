package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.direction.MeasureNumberingType;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.layout.MeasureLayout;

public class PrintBuilder extends MusicDataBuilder {
    private Print print;

    public PrintBuilder(Print print) {
        this.print = print;
    }

    public StringBuilder build() {
        if (print == null) return stringBuilder;

        buildOpenElement("print");
        buildAttribute("staff-spacing", print.getStaffSpacing());
        buildAttribute("new-system",  print.getNewSystem());
        buildAttribute("new-page",  print.getNewPage());
        buildAttribute("blank-page", print.getBlankPage());
        buildAttribute("page-number", print.getPageNumber());
        buildCloseElement();
        append(LayoutBuilder.buildLayout(print.getLayout()));
        MeasureLayout measureLayout = print.getMeasureLayout();
        if (measureLayout != null) {
            buildStartElement("measure-layout");
            buildElementWithValue("measure-distance", measureLayout.getMeasureDistance());
            buildEndElement("measure-layout");
        }
        MeasureNumberingType measureNumberingValue = print.getMeasureNumberingValue();
        if (measureNumberingValue != null) buildElementWithValueAndAttributes("measure-numbering", measureNumberingValue, DisplayBuilder.buildDisplay(print.getMeasureNumberingDisplay()));
        buildNameDisplay("part-name-display", print.getPartNameDisplay());
        buildNameDisplay("part-abbreviation-display", print.getPartAbbreviationDisplay());
        buildEndElement("print");

        return stringBuilder;
    }
}
