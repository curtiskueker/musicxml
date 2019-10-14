package org.curtis.musicxml.util;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.common.XmlComment;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Grouping;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.FiguredBass;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.MeasureItem;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScoreName;
import org.curtis.properties.AppProperties;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlException;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicXmlUtil {
    private static DBSessionFactory sessionFactory;
    public static Boolean DEBUG = false;
    public static Boolean INCLUDE_BREAKS = false;

    private MusicXmlUtil() {

    }

    public static DBTransaction getDbTransaction() throws DBException {
        if (sessionFactory == null) {
            AppProperties.setPrefix("musicxml");
            AppProperties.addPropertiesFile("properties/database");
            AppProperties.addLocalPropertiesBundle();

            sessionFactory = DBSessionFactory.getInstance();
        }

        return sessionFactory.getTransaction();
    }

    public static DBTransaction getNewDbTransaction() throws DBException {
        clearDb();

        return getDbTransaction();
    }

    public static void clearDb() throws DBException {
        sessionFactory = null;
        DBSessionFactory.clearSessionFactory();
    }

    public static String getFormattedXml(Document document) throws XmlException {
        try {
            document.normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']",
                    document,
                    XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                node.getParentNode().removeChild(node);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 4);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new XmlException(e);
        }
    }

    public static MusicData getMusicDataForMeasureItem(MeasureItem measureItem) throws MusicXmlException {
        if (measureItem == null) throw new MusicXmlException("Measure Item not found");

        Integer measureItemId = measureItem.getId();
        String musicDataType = measureItem.getMusicDataType();

        try {
            switch (musicDataType) {
                case "note":
                    return getDbTransaction().getObjectById(Note.class, measureItemId);
                case "backup":
                    return getDbTransaction().getObjectById(Backup.class, measureItemId);
                case "forward":
                    return getDbTransaction().getObjectById(Forward.class, measureItemId);
                case "direction":
                    return getDbTransaction().getObjectById(Direction.class, measureItemId);
                case "attributes":
                    return getDbTransaction().getObjectById(Attributes.class, measureItemId);
                case "harmony":
                    return getDbTransaction().getObjectById(Harmony.class, measureItemId);
                case "figured bass":
                    return getDbTransaction().getObjectById(FiguredBass.class, measureItemId);
                case "print":
                    return getDbTransaction().getObjectById(Print.class, measureItemId);
                case "sound":
                    return getDbTransaction().getObjectById(Sound.class, measureItemId);
                case "barline":
                    return getDbTransaction().getObjectById(Barline.class, measureItemId);
                case "grouping":
                    return getDbTransaction().getObjectById(Grouping.class, measureItemId);
                case "link":
                    return getDbTransaction().getObjectById(Link.class, measureItemId);
                case "bookmark":
                    return getDbTransaction().getObjectById(Bookmark.class, measureItemId);
                default:
                    throw new MusicXmlException("MeasureItem type not found");
            }
        } catch (DBException e) {
            throw new MusicXmlException(e);
        }
    }

    public static List<String> getScoreNames() {
        List<String> scoreNames = new ArrayList<>();

        try {
            List<ScoreName> scores = getDbTransaction().findAll(ScoreName.class);
            for (ScoreName score : scores) scoreNames.add(score.getScoreName());
            Collections.sort(scoreNames, String.CASE_INSENSITIVE_ORDER);
        } catch (DBException e) {
            //
        }

        return scoreNames;
    }

    public static List<XmlComment> getXmlComments(Node node) {
        return getXmlComments(node, "");
    }

    private static List<XmlComment> getXmlComments(Node node, String path) {
        List<XmlComment> xmlComments = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();
        Map<String, Integer> nodeNameMap = new HashMap<>();
        List<XmlComment> currentComments = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                String nodeName = childNode.getNodeName();
                Integer nodeCount = nodeNameMap.computeIfAbsent(nodeName, name -> 0);
                nodeCount++;
                nodeNameMap.put(nodeName, nodeCount);
                String nodePath = nodeName + "[" + nodeCount + "]";
                if (!currentComments.isEmpty()) {
                    for (XmlComment xmlComment : currentComments) xmlComment.setNextSibling(nodePath);
                    currentComments.clear();
                }
                if (childNode.hasChildNodes()) xmlComments.addAll(getXmlComments(childNode, path + "/" + nodePath));
            }
            if (childNode.getNodeType() == Node.COMMENT_NODE || childNode.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {
                XmlComment xmlComment = new XmlComment();
                if (childNode.getNodeType() == Node.COMMENT_NODE) {
                    Comment comment = (Comment)childNode;
                    xmlComment.setData(comment.getData());
                } else if (childNode.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {
                    ProcessingInstruction processingInstruction = (ProcessingInstruction)childNode;
                    xmlComment.setTarget(processingInstruction.getTarget());
                    xmlComment.setData(processingInstruction.getData());
                }
                xmlComment.setParent(path);
                currentComments.add(xmlComment);
                xmlComments.add(xmlComment);
            }
        }

        return xmlComments;
    }

    public static void setXmlComments(Document document, List<XmlComment> xmlComments) {
        if (document == null || xmlComments == null || xmlComments.isEmpty()) return;

        XPath xPath = XPathFactory.newInstance().newXPath();
        Map<String, Node> nodeMap = new HashMap<>();
        for (XmlComment xmlComment : xmlComments) {
            try {
                Node commentNode;
                if (xmlComment.getTarget() == null) commentNode = document.createComment(xmlComment.getData());
                else commentNode = document.createProcessingInstruction(xmlComment.getTarget(), xmlComment.getData());

                String parentLocation = xmlComment.getParent();
                Node parentNode = nodeMap.get(parentLocation);
                if (parentNode == null) {
                    if (StringUtil.isEmpty(parentLocation)) {
                        parentNode = (Node)xPath.evaluate("/", document, XPathConstants.NODE);
                    } else {
                        parentNode = (Node)xPath.evaluate(parentLocation, document, XPathConstants.NODE);
                        nodeMap.put(parentLocation, parentNode);
                    }
                }
                String nextSibling = xmlComment.getNextSibling();
                if (StringUtil.isNotEmpty(nextSibling)) {
                    String siblingLocation = parentLocation + "/" + nextSibling;
                    Node siblingNode = nodeMap.get(siblingLocation);
                    if (siblingNode == null) {
                        siblingNode = (Node)xPath.evaluate(nextSibling, parentNode, XPathConstants.NODE);
                        nodeMap.put(siblingLocation, siblingNode);
                    }
                    parentNode.insertBefore(commentNode, siblingNode);
                } else parentNode.appendChild(commentNode);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        }
    }
}
