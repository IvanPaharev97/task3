package com.epam.task3.main;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.task3.entity.Dish;
import com.epam.task3.entity.Product;
import com.epam.task3.util.DishManager;
import com.epam.task3.util.ProductManager;
import com.epam.task3.util.SaladManager;
import com.epam.task3.util.VegetableManager;

public class Main {

    public static void main(String[] args) {
        File chefData = new File("resources/chefData.xml");
        DomDocumentParser parser = new DomDocumentParser(chefData);
        ProductManager vegetableManager = new VegetableManager(parser.getNodeList("vegetable"));
        SaladManager saladManager = new SaladManager(vegetableManager, parser.getNodeList("salad"));
        System.out.println(vegetableManager.getProduct("Potato"));
        Dish salad = saladManager.getDish("Vitaminic");
        System.out.println(salad.getCaloricity());
        vegetableManager.loadAllProducts();
        List<Product> vegetables = vegetableManager.getLoadedProducts();
        ProductManager.sort(vegetables, new Comparator<Product>() {
            @Override
            public int compare(Product arg0, Product arg1) {
                if (arg0.getName().compareTo(arg1.getName()) > 0) {
                    return 1;
                }
                if (arg0.getName().compareTo(arg1.getName()) < 0) {
                    return -1;
                }
                return 0;
            }
        }, false);
        System.out.println(saladManager.getDish("Freshy"));
        System.out.println(vegetables);
        System.out.println(ProductManager.getProductsByCaloricity(vegetables, 0.6, 0.7));
        System.out.println(DishManager.getSaladsByCaloricity(saladManager.getLoadedDishes(), 300, 1000));
    }
    
}

class DomDocumentParser {
    
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
