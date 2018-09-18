package org.curtis.xml;

import org.curtis.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {
    public static Document stringToDocument(String xmlString) throws XmlException {
        if (xmlString == null) {
            throw new XmlException("Null Xml String");
        }

        xmlString = stringToXml(xmlString);

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setErrorHandler(new XmlErrorHandler());
            builder.setEntityResolver(new XmlEntityResolver());

            return builder.parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes())));
        } catch (Exception e) {
            throw new XmlException(e.getMessage());
        }
    }

    public static Document fileToDocument(File xmlFile) throws XmlException {
        if (xmlFile == null) {
            throw new XmlException("Invalid file");
        }

        // Read file as an xml document
        StringBuilder xmlStringBuilder = new StringBuilder();

        try {
            FileReader reader = new FileReader(xmlFile);
            int ch;
            boolean inProlog = true;
            while ((ch = reader.read()) != -1) {
                char c = (char)ch;
                if (c == '<') inProlog = false;
                if (inProlog) continue;
                xmlStringBuilder.append(c);
            }
        } catch (IOException e) {
            throw new XmlException(e.getMessage());
        }

        return stringToDocument(xmlStringBuilder.toString());
    }

    public static String elementToString(Element element) throws XmlException {
        if (element == null) {
            throw new XmlException("Null Element");
        }

        try {
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer trans = transFactory.newTransformer();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            trans.transform(new DOMSource(element), new StreamResult(outputStream));

            return outputStream.toString();
        } catch (Exception e) {
            throw new XmlException(e.getMessage());
        }
    }

    public static String stringToXml(String xmlString) {
        if (xmlString == null) {
            return null;
        }

        // Replace & value
        xmlString = xmlString.replaceAll("&amp;", "&");
        xmlString = xmlString.replaceAll("&", "&amp;");

        return xmlString;
    }

    public static Element getChildElement(Element element, String childElementName) {
        if (element == null || childElementName == null) {
            return null;
        }

        NodeList children = element.getElementsByTagNameNS("*", childElementName);

        if (children.getLength() == 0) {
            children = element.getElementsByTagName(childElementName);
        }

        // No such child, return null
        if (children.getLength() == 0) {
            return null;
        }

        return (Element) children.item(0);
    }

    public static boolean hasChildElement(Element element, String childElementName) {
        return getChildElement(element, childElementName) != null;
    }

    public static List<Element> getChildElements(Element element, String childElementName) {
        List<Element> elements = new ArrayList<>();

        if (element == null || childElementName == null) {
            return elements;
        }

        NodeList children = element.getElementsByTagNameNS("*", childElementName);

        if (children.getLength() == 0) {
            children = element.getElementsByTagName(childElementName);
        }

        int length = children.getLength();

        for (int i = 0; i < length; i++) {
            elements.add((Element) children.item(i));
        }

        return elements;
    }

    public static List<Element> getChildElements(Element element) {
        List<Element> elements = new ArrayList<>();

        if (element == null) {
            return elements;
        }

        Node childNode = element.getFirstChild();
        while (childNode != null) {
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;
                elements.add(childElement);
            }
            childNode = childNode.getNextSibling();
        }

        return elements;
    }

    public static String getElementText(Element element) {
        if (element == null) {
            return "";
        }

        String elementText = "";

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.TEXT_NODE) {
                elementText = ((Text) node).getData();
            }
        }

        return elementText;
    }

    public static String getChildElementText(Element element, String childElementName) {
        if (element == null || childElementName == null) {
            return "";
        }

        Element childElement = getChildElement(element, childElementName);

        // No such child, return an empty String
        if (childElement == null) {
            return "";
        }

        return getElementText(childElement);
    }
}