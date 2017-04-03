package com.epam.task3.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomDocumentParser {
    
    final static Logger logger = Logger.getLogger(DomDocumentParser.class);
    
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    
    public DomDocumentParser(File xmlFile) {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
            document.normalize();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error("Exception with file parsing: ", e);
        }
    }
    
    public void setDocument(File xmlFile) {
        try {
            document = documentBuilder.parse(xmlFile);
            document.normalize();
        } catch (IOException | SAXException e) {
            logger.error("Exception with file parsing: ", e);
        }
    }
    
    public NodeList getNodeList(String tagName) {
        return document.getElementsByTagName(tagName);
    }

    @Override
    public String toString() {
        return "DomDocumentParser [documentBuilderFactory=" + documentBuilderFactory + ", documentBuilder="
                + documentBuilder + ", document=" + document + "]";
    }
    
}
