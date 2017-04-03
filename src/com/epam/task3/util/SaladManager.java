package com.epam.task3.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epam.task3.salad.Salad;
import com.epam.task3.vegetable.Vegetable;

public class SaladManager {

    private static final Logger log = Logger.getLogger(SaladManager.class.getName());
    
    private VegetableManager vegetableManager;
    private HashMap<String, Salad> salads;
    private NodeList saladsNodes;
    
    public SaladManager(VegetableManager vegetableManager, NodeList saladsNodes) {
        this.saladsNodes = saladsNodes;
        this.vegetableManager = vegetableManager;
        salads = new HashMap<>();
    }
    
    public Salad getSalad(String name) {
        Salad salad = salads.get(name);
        if (salad == null) {
            salad = makeSalad(name);
            salads.put(name, salad);
        }
        return salad;
    }
    
    public List<Salad> getLoadedSalads() {
        return new ArrayList<>(salads.values());
    }
    
    public List<Salad> getSalads(Set<String> saladsNames) {
        List<Salad> saladsList = new ArrayList<>();
        Salad salad;
        for (String saladName : saladsNames) {
            if (!salads.containsKey(saladName)) {
                salad = makeSalad(saladName);
                salads.put(saladName, salad);
            } else {
                salad = salads.get(saladName);
            }
            saladsList.add(salad);
        }
        return saladsList;
    }
    
    public List<Salad> loadAllSalads() {
        for (int i = 0; i < saladsNodes.getLength(); i++) {
            String saladName = ((Element)saladsNodes.item(i)).getElementsByTagName("name").item(0).getTextContent();
            if (!salads.containsKey(saladName)) {
                salads.put(saladName, makeSalad(saladName));
            }
        }
        return getLoadedSalads();
    }
    
    @Override
    public String toString() {
        return "SaladManager [vegetableManager=" + vegetableManager + ", salads=" + salads + ", saladsNodes="
                + saladsNodes + "]";
    }
   
    public static void sort(List<Salad> salads, boolean ascending) {
        quickSort(salads, 0, salads.size() - 1, ascending);
    }
    
    public static void sort(List<Salad> salads, Comparator<? super Salad> comp, boolean ascending) {
        quickSort(salads, comp, 0, salads.size() - 1, ascending);
    }
    
    public static List<Salad> getSaladsByCaloricity(List<Salad> salads, double from, double to) {
        List<Salad> foundSalads = new ArrayList<>();
        for (Salad salad : salads) {
            if (salad.getCaloricity() >= from && salad.getCaloricity() <= to) {
                foundSalads.add(salad);
            }
        }
        return foundSalads;
    }
    
    private static void quickSort(List<Salad> salads, int from, int to, boolean ascending) {
        int startFrom = from;
        int startTo = to;
        Salad pivot = salads.get((from + to) / 2);
        while (from <= to) {
            if (ascending) {
                while (salads.get(from).compareTo(pivot) < 0)
                    from++;
                while (salads.get(to).compareTo(pivot) > 0)
                    to--;
                if (from <= to) {
                    Salad temp = salads.get(from);
                    salads.set(from, salads.get(to));
                    salads.set(to, temp);
                    from++;
                    to--;
                }
            } else {
                while (salads.get(from).compareTo(pivot) > 0)
                    from++;
                while (salads.get(to).compareTo(pivot) < 0)
                    to--;
                if (from <= to) {
                    Salad temp = salads.get(from);
                    salads.set(from, salads.get(to));
                    salads.set(to, temp);
                    from++;
                    to--;
                }    
            }
        }
        if (from - 1 > startFrom)
            quickSort(salads, startFrom, from - 1, ascending);
        if (from < startTo)
            quickSort(salads, from, startTo, ascending);
    }
    
    private static void quickSort(List<Salad> salads, Comparator<? super Salad> comp, int from, int to,  boolean ascending) {
        int startFrom = from;
        int startTo = to;
        Salad pivot = salads.get((from + to) / 2);
        while (from <= to) {
            if (ascending) {
                while (comp.compare(salads.get(from), pivot) < 0)
                    from++;
                while (comp.compare(salads.get(to), pivot) > 0)
                    to--;
                if (from <= to) {
                    Salad temp = salads.get(from);
                    salads.set(from, salads.get(to));
                    salads.set(to, temp);
                    from++;
                    to--;
                }
            } else {
                while (comp.compare(salads.get(from), pivot) > 0)
                    from++;
                while (comp.compare(salads.get(to), pivot) < 0)
                    to--;
                if (from <= to) {
                    Salad temp = salads.get(from);
                    salads.set(from, salads.get(to));
                    salads.set(to, temp);
                    from++;
                    to--;
                }
            }
        }
        if (from - 1 > startFrom)
              quickSort(salads, comp, startFrom, from - 1, ascending);
        if (from < startTo)
              quickSort(salads, comp, from, startTo, ascending);
    }
        
    private Salad makeSalad(String name) {
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
        HashMap<Vegetable, Double> vegetablesInSalad = getVegetablesInSalad(saladElement);
        double caloricity = 0;
        double weight = 0;
        for (Map.Entry<Vegetable, Double> pair : vegetablesInSalad.entrySet()) {
            caloricity += pair.getValue() * pair.getKey().getCaloricity();
            weight += pair.getValue();
        }
        Salad salad = new Salad(name, caloricity, weight, vegetablesInSalad);
        salads.put(name, salad);
        return salad;
    }

    private HashMap<Vegetable, Double> getVegetablesInSalad(Element saladElement) {
        HashMap<String, Double> vegetablesAndWeights = new HashMap<>();
        NodeList vegetablesNodes = saladElement.getElementsByTagName("vegName");
        for (int i = 0; i < vegetablesNodes.getLength(); i++) {
            Element vegetableElement = (Element) vegetablesNodes.item(i);
            vegetablesAndWeights.put(vegetableElement.getTextContent(),
                                     Double.parseDouble(vegetableElement.getAttribute("weight")));
        }
        List<Vegetable> vegetables = vegetableManager.getVegetables(vegetablesAndWeights.keySet());
        HashMap<Vegetable, Double> vegetablesInSalad = new HashMap<>();
        for (Vegetable vegetable : vegetables) {
            vegetablesInSalad.put(vegetable, vegetablesAndWeights.get(vegetable.getName()));
        }
        return vegetablesInSalad;
    }


}
