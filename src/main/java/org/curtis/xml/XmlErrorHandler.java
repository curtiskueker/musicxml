package org.curtis.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlErrorHandler implements ErrorHandler {
    public XmlErrorHandler() {

    }

    public void warning(SAXParseException e) throws SAXException {
        System.err.println("Warning: " + e.getMessage());
        // Do nothing
    }

    public void error(SAXParseException e) throws SAXException {
        // Throw a SAXException
        throw new SAXException("XML Parsing Error: " + e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXException {
        // Throw a SAXException
        throw new SAXException("XML Parsing Fatal Error: " + e.getMessage());
    }
}
