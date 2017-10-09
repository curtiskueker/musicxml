package org.curtis.musicxml.identity.encoding;

public class Supports extends Encoding {
    // TODO: type
    private String element;
    private String attribute;
    private String value;

    public Supports() {

    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
