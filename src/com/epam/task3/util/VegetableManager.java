package com.epam.task3.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.epam.task3.entity.Product;
import com.epam.task3.entity.Vegetable;

import static com.epam.task3.entity.Vegetable.VegetableBuilder;

public class VegetableManager extends AbstractProductManager implements ProductManager {
    
    private static final Logger log = Logger.getLogger(SaladManager.class.getName());
    
    private NodeList vegetablesNodes;
    
    public VegetableManager(NodeList vegetablesNodes) {
        this.vegetablesNodes = vegetablesNodes;
    }
    
    @Override
    public List<Product> loadAllProducts() {
        for (int i = 0; i < vegetablesNodes.getLength(); i++) {
            String productName = ((Element)vegetablesNodes.item(i)).getElementsByTagName("name").item(0).getTextContent();
            if (!products.containsKey(productName)) {
                products.put(productName, findProduct(productName));
            }
        }
        return getLoadedProducts();
    }

    @Override
    protected Vegetable findProduct(String name) {
        Element vegElement = null;
        for (int i = 0; i < vegetablesNodes.getLength(); i++) {
            Node node = ((Element)vegetablesNodes.item(i)).getElementsByTagName("name").item(0);
            if (node.getTextContent().equals(name)) {
                vegElement = (Element) vegetablesNodes.item(i);
            }
        }
        if (vegElement == null) {
            IllegalArgumentException exception = new IllegalArgumentException("Product with name <" + name + "> not found");
            log.error("Error in method findProduct: ", exception);
            throw exception;
        }
        Vegetable vegetable = new VegetableBuilder(vegElement.getElementsByTagName("name").item(0).getTextContent(),
                                                   getDoubleByTagName(vegElement, "caloricityPerGramm"))
                .setProteinsPerGramm(getDoubleByTagName(vegElement, "proteinsPerGramm"))
                .setFatPerGramm(getDoubleByTagName(vegElement, "fatPerGramm"))
                .setCarbohydratesPerGramm(getDoubleByTagName(vegElement, "carbohydratesPerGramm"))
                .build();
        products.put(name, vegetable);
        return vegetable;
    }

    private double getDoubleByTagName(Element vegElement, String tagName) {
        return Double.parseDouble(vegElement.getElementsByTagName(tagName).item(0).getTextContent());        
    }
    
}
