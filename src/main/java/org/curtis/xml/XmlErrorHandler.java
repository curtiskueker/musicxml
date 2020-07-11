package org.curtis.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlErrorHandler implements ErrorHandler {
    private int errorCount = 0;
    private StringBuilder errors = new StringBuilder();

    public XmlErrorHandler() {

    }

    public void warning(SAXParseException e) throws SAXException {
        System.err.println("Warning: " + e.getMessage());
    }

    public void error(SAXParseException e) throws SAXException {
        appendError("XML Parsing Error: " + e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXException {
        appendError("XML Parsing Fatal Error: " + e.getMessage());
    }

    public boolean hasErrors() {
        return errorCount > 0;
    }

    public StringBuilder getErrors() {
        if (hasErrors()) errors.insert(0, "XML Error count: " + errorCount + "\n");
        return errors;
    }

    private void appendError(String errorMessage) {
        errorCount++;
        errors.append(errorCount);
        errors.append(": ");
        errors.append(errorMessage);
        errors.append("\n");
    }
}
