package org.curtis.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public class XmlEntityResolver implements EntityResolver {
    public XmlEntityResolver() {

    }

    public InputSource resolveEntity(String publicId, String systemId) {
        try {
            return new InputSource(new ByteArrayInputStream("".getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
