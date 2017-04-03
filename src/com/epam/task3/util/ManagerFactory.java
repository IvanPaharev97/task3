package com.epam.task3.util;

import java.io.File;

import org.w3c.dom.NodeList;

public class ManagerFactory {
    
    private static DomDocumentParser parser;
    private static File lastXmlFile;
    
    public static VegetableManager getVegetableManager(File xmlFile) {
        return new VegetableManager(getNodeList("vegetable", xmlFile));
    }
    
    public static SaladManager getSaladManager(File xmlFile) {
        return new SaladManager(getVegetableManager(xmlFile), getNodeList("salad", xmlFile));
    }
    
    private static NodeList getNodeList(String tagName, File xmlFile) {
        if (!xmlFile.equals(lastXmlFile)) {
            loadParser(xmlFile);
        }
        return parser.getNodeList(tagName);
    }

    private static void loadParser(File xmlFile) {
        if (parser != null) {
            parser.setDocument(xmlFile);
        } else {
            parser = new DomDocumentParser(xmlFile);
        }
    }
}
