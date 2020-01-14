package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.barline.BarStyleColor;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Ending;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.note.notation.Fermata;

import java.util.HashMap;
import java.util.Map;

public class BarlineBuilder extends MusicDataBuilder {
    private Barline barline;

    public BarlineBuilder(Barline barline) {
        this.barline = barline;
    }

    public StringBuilder build() {
        if (barline == null) return stringBuilder;

        buildOpenElement("barline");
        buildAttribute("location", barline.getLocation());
        buildAttribute("segno", barline.getSegno());
        buildAttribute("coda", barline.getCoda());
        buildAttribute("divisions", barline.getDivisions());
        buildCloseElement();
        BarStyleColor barStyleColor = barline.getBarStyle();
        if (barStyleColor != null) {
            buildElementWithValueAndAttribute("bar-style", barStyleColor.getBarStyle(), "color", barStyleColor.getColor());
        }
        buildEditorial(barline.getEditorial());
        buildWavyLine(barline.getWavyLine());
        buildSegno(barline.getSegnoPrint());
        buildCoda(barline.getCodaPrint());
        for (Fermata fermata : barline.getFermataList()) buildFermata(fermata);
        Ending ending = barline.getEnding();
        if (ending != null) {
            Map<String, String> attributes = new HashMap<>();
            attributes.put("number", BuilderUtil.requiredValue(ending.getNumber()));
            attributes.put("type", BuilderUtil.enumValue(ending.getType()));
            attributes.put("print-object", BuilderUtil.yesOrNo(ending.getPrintObject()));
            attributes.putAll(FormattingBuilder.buildPrintStyle(ending.getPrintStyle()));
            attributes.put("end-length", BuilderUtil.stringValue(ending.getEndLength()));
            attributes.put("text-x", BuilderUtil.stringValue(ending.getTextX()));
            attributes.put("text-y", BuilderUtil.stringValue(ending.getTextY()));
            buildElementWithValueAndAttributes("ending", ending.getValue(), attributes);
        }
        Repeat repeat = barline.getRepeat();
        if (repeat != null) {
            Map<String, String> attributes = new HashMap<>();
            attributes.put("direction", BuilderUtil.enumValue(repeat.getDirection()));
            attributes.put("times", BuilderUtil.stringValue(repeat.getTimes()));
            attributes.put("winged", BuilderUtil.enumValue(repeat.getWinged()));
            buildElementWithAttributes("repeat", attributes);
        }
        buildEndElement("barline");

        return stringBuilder;
    }
}
