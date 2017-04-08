package com.epam.task3.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epam.task3.entity.Dish;
import com.epam.task3.entity.Product;
import com.epam.task3.entity.Salad;

public class SaladManager extends AbstractDishManager implements DishManager{

    private static final Logger log = Logger.getLogger(SaladManager.class.getName());
    
    private NodeList saladsNodes;
    
    public SaladManager(ProductManager productManager, NodeList saladsNodes) {
        super(productManager);
        this.saladsNodes = saladsNodes;
    }
   
    @Override
    public List<Dish> loadAllDishes() {
        for (int i = 0; i < saladsNodes.getLength(); i++) {
            String saladName = ((Element)saladsNodes.item(i)).getElementsByTagName("name").item(0).getTextContent();
            if (!dishes.containsKey(saladName)) {
                dishes.put(saladName, makeDish(saladName));
            }
        }
        return getLoadedDishes();
    }
    
    @Override
    public String toString() {
        return "SaladManager [vegetableManager=" + getProductManager() + ", salads=" + dishes + ", saladsNodes="
                + saladsNodes + "]";
    }
       
    @Override
    protected Salad makeDish(String name) {
        Element saladElement = null;
        for (int i = 0; i < saladsNodes.getLength(); i++) {
            Node node = ((Element)saladsNodes.item(i)).getElementsByTagName("name").item(0);
            if (node.getTextContent().equals(name)) {
                saladElement = (Element) saladsNodes.item(i);
            }
        }
        if (saladElement == null) {
            IllegalArgumentException exception = new IllegalArgumentException("Salad with name <" + name + "> not found");
            log.error("Error in method makeSalad: ", exception);
            throw exception;
        }
        HashMap<Product, Double> vegetablesInSalad = getVegetablesInSalad(saladElement);
        double caloricity = 0;
        double weight = 0;
        for (Map.Entry<Product, Double> pair : vegetablesInSalad.entrySet()) {
            caloricity += pair.getValue() * pair.getKey().getCaloriesPerGramm();
            weight += pair.getValue();
        }
        Salad salad = new Salad(name, caloricity, weight, vegetablesInSalad);
        dishes.put(name, salad);
        return salad;
    }

    private HashMap<Product, Double> getVegetablesInSalad(Element saladElement) {
        HashMap<String, Double> vegetablesAndWeights = new HashMap<>();
        NodeList vegetablesNodes = saladElement.getElementsByTagName("vegName");
        for (int i = 0; i < vegetablesNodes.getLength(); i++) {
            Element vegetableElement = (Element) vegetablesNodes.item(i);
            vegetablesAndWeights.put(vegetableElement.getTextContent(),
                                     Double.parseDouble(vegetableElement.getAttribute("weight")));
        }
        List<Product> vegetables = getProductManager().getProducts(vegetablesAndWeights.keySet());
        HashMap<Product, Double> vegetablesInSalad = new HashMap<>();
        for (Product vegetable : vegetables) {
            vegetablesInSalad.put(vegetable, vegetablesAndWeights.get(vegetable.getName()));
        }
        return vegetablesInSalad;
    }

}
