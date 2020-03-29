package org.curtis.xml;

import org.curtis.exception.FileException;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CompressedXmlUtil {
    private static final String CONTAINER_FILENAME = "META-INF/container.xml";
    private static final String ROOTFILE_ELEMENT = "rootfile";
    private static final String FULL_PATH_ATTRIBUTE = "full-path";

    private CompressedXmlUtil() {

    }

    public static boolean isCompressedFile(String filename) {
        return FileUtil.getFileExtension(filename).equals("mxl");
    }

    public static Document getCompressedDocument(File zippedFile) throws FileException {
        if (zippedFile == null) throw new FileException("Invalid zipped file");

        String zippedFilename = zippedFile.getAbsolutePath();
        try {
            ZipFile zipFile = new ZipFile(zippedFile);
            ZipEntry containerEntry = zipFile.getEntry(CONTAINER_FILENAME);
            if (containerEntry == null) throw new FileException("Container file META-INF/container.xml not found in file " + zippedFilename);

            String containerFileContents = XmlUtil.readXmlToString(new InputStreamReader(zipFile.getInputStream(containerEntry)));
            Document containerDocument = XmlUtil.stringToDocument(containerFileContents);
            List<Element> rootfileElements = XmlUtil.getChildElements(containerDocument.getDocumentElement(), ROOTFILE_ELEMENT);
            if (rootfileElements.isEmpty()) throw new FileException("rootfile element not found in container file " + CONTAINER_FILENAME);

            String xmlFilename = null;
            for (Element rootfileElement : rootfileElements) {
                String filename = rootfileElement.getAttribute(FULL_PATH_ATTRIBUTE);
                if (FileUtil.isXmlFileExtension(filename)) {
                    xmlFilename = filename;
                    break;
                }
            }
            if (StringUtil.isEmpty(xmlFilename)) throw new FileException("full-path attribute of rootfile element missing in container file " + CONTAINER_FILENAME);

            ZipEntry xmlEntry = zipFile.getEntry(xmlFilename);
            if (xmlEntry == null) throw new FileException("Xml file " + xmlFilename + " not found in zip file " + zippedFilename);

            String xmlFileContents = XmlUtil.readXmlToString(new InputStreamReader(zipFile.getInputStream(xmlEntry)));
            return XmlUtil.stringToDocument(xmlFileContents);
        } catch (IOException | XmlException e) {
            throw new FileException(e.getMessage());
        }
    }
}
