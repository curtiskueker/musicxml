package org.curtis.musicxml.handler;

import org.curtis.musicxml.note.notation.Notation;
import org.w3c.dom.Element;

public interface NotationHandler {
    Notation handle(Element element);
}
