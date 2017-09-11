package org.curtis.xml;

import org.curtis.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class XmlUtil {
    public static Document stringToDocument(String xmlString) throws XmlException {
        if(xmlString == null){
            throw new XmlException("Null Xml String");
        }

        xmlString = stringToXml(xmlString);

        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setErrorHandler(new XmlErrorHandler());

            return builder.parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes())));
        }
        catch(Exception e){
            throw new XmlException(e.getMessage());
        }
    }

    public static Document fileToDocument(String xmlFilename) throws XmlException {
        if(StringUtil.isEmpty(xmlFilename)){
            throw new XmlException("Invalid filename");
        }

        // Read file as an xml document
        StringBuilder xmlStringBuilder = new StringBuilder();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(xmlFilename));
            String line;
            while((line = reader.readLine()) != null){
                xmlStringBuilder.append(line);
                xmlStringBuilder.append("\n");
            }
        }
        catch(IOException e){
            throw new XmlException(e.getMessage());
        }

        return stringToDocument(xmlStringBuilder.toString());
    }

    public static String elementToString(Element element) throws XmlException {
        if(element == null){
            throw new XmlException("Null Element");
        }

        try{
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer trans = transFactory.newTransformer();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            trans.transform(new DOMSource(element), new StreamResult(outputStream));

            return outputStream.toString();
        }
        catch(Exception e){
            throw new XmlException(e.getMessage());
        }
    }

    public static String stringToXml(String xmlString){
        if(xmlString == null){
            return null;
        }

        // Replace & value
        xmlString = xmlString.replaceAll("&amp;", "&");
        xmlString = xmlString.replaceAll("&", "&amp;");

        return xmlString;
    }
}
