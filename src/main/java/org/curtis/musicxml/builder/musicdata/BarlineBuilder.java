package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.barline.BarStyle;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.BarlineEnding;
import org.curtis.musicxml.barline.BarlineRepeat;
import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.display.Display;
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
        buildAttribute("id", barline.getElementId());
        buildAttribute("location", barline.getBarlineLocation());
        buildAttribute("segno", barline.getSegno());
        buildAttribute("coda", barline.getCoda());
        buildAttribute("divisions", barline.getDivisions());
        buildCloseElement();
        BarStyle barStyle = barline.getBarStyle();
        if (barStyle != null) {
            Display barlineDisplay = barline.getDisplay();
            String barlineColor = barlineDisplay == null ? "" : barlineDisplay.getColor();
            buildElementWithValueAndAttribute("bar-style", barStyle, "color", barlineColor);
        }
        buildEditorial(barline.getEditorial());
        buildWavyLine(barline.getWavyLine());
        buildSegno(barline.getSegnoPrint());
        buildCoda(barline.getCodaPrint());
        for (Fermata fermata : barline.getFermataList()) buildFermata(fermata);
        BarlineEnding ending = barline.getEnding();
        if (ending != null) {
            Map<String, String> attributes = new HashMap<>();
            attributes.put("number", BuilderUtil.requiredValue(ending.getNumber()));
            attributes.put("type", BuilderUtil.enumValue(ending.getType()));
            attributes.put("print-object", BuilderUtil.yesOrNo(ending.getPrintObject()));
            attributes.putAll(DisplayBuilder.buildDisplay(ending.getDisplay()));
            attributes.put("end-length", BuilderUtil.stringValue(ending.getEndLength()));
            attributes.put("text-x", BuilderUtil.stringValue(ending.getTextX()));
            attributes.put("text-y", BuilderUtil.stringValue(ending.getTextY()));
            buildElementWithValueAndAttributes("ending", ending.getValue(), attributes);
        }
        BarlineRepeat repeat = barline.getRepeat();
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
