package org.curtis.musicxml.factory;

import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.util.StringUtil;
import org.w3c.dom.Element;

public class OrnamentFactory {
    private OrnamentFactory() {

    }

    public static WavyLine newWavyLine(Element wavyLineElement) {
        if(wavyLineElement == null) return null;

        WavyLine wavyLine = new WavyLine();
        wavyLine.setType(PlacementUtil.getConnection(wavyLineElement.getAttribute("type")));
        wavyLine.setNumber(StringUtil.getInteger(wavyLineElement.getAttribute("number")));
        wavyLine.setPosition(FormattingFactory.newPosition(wavyLineElement));
        wavyLine.setPlacement(PlacementUtil.getLocation(wavyLineElement.getAttribute("placement")));
        wavyLine.setColor(wavyLineElement.getAttribute("color"));
        // TODO: trill-sound

        return wavyLine;
    }
}
