package org.curtis.musicxml.handler;

import org.w3c.dom.Element;

public abstract class AbstractHandler {
    private Element element;
    private StringBuilder stringBuilder = new StringBuilder();

    protected AbstractHandler(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public abstract StringBuilder handle();
}
