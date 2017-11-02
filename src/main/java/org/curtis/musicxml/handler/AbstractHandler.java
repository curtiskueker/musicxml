package org.curtis.musicxml.handler;

import org.w3c.dom.Element;

public abstract class AbstractHandler {
    private Element element;

    protected AbstractHandler(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public abstract void handle();
}