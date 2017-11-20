package org.curtis.musicxml.handler;

import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class ForwardHandler extends MusicDataHandler {
    public ForwardHandler() {

    }

    public MusicData handle(Element element) {
        Forward forward = new Forward();

        forward.setDuration(MathUtil.newBigDecimal(XmlUtil.getChildElementText(element, "duration")));
        forward.setStaff(StringUtil.getInteger(XmlUtil.getChildElementText(element, "staff")));

        return forward;
    }
}
