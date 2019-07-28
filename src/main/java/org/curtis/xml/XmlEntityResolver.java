package org.curtis.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class XmlEntityResolver implements EntityResolver {
    public XmlEntityResolver() {

    }

    public InputSource resolveEntity(String publicId, String systemId) {
        return new InputSource(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
    }
}
