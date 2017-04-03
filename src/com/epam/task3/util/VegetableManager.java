package com.epam.task3.util;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epam.task3.vegetable.Vegetable;

import static com.epam.task3.vegetable.Vegetable.VegetableBuilder;
public class VegetableManager {

    private static final Logger log = Logger.getLogger(SaladManager.class.getName());
    
    private HashMap<String, Vegetable> vegetables = new HashMap<>();
    private NodeList vegetablesNodes;
    
    public VegetableManager(NodeList vegetableNodes) {
        this.vegetablesNodes = vegetableNodes;
    }
    
    public Vegetable getVegetable(String name) {
        Vegetable vegetable = vegetables.get(name);
        if (vegetable == null) {
            vegetable = findVegetable(name);
            vegetables.put(name, vegetable);
        }
        return vegetable;
    }

    public List<Vegetable> getVegetables(Set<String> vegetablesNames) {
        List<Vegetable> vegetableList = new ArrayList<>();
        Vegetable vegetable;
        for (String vegetableName : vegetablesNames) {
            if (!vegetables.containsKey(vegetableName)) {
                vegetable = findVegetable(vegetableName);
                vegetables.put(vegetableName, vegetable);
            } else {
                vegetable = vegetables.get(vegetableName);
            }
            vegetableList.add(vegetable);
        }
        return vegetableList;
    }
    
    public List<Vegetable> getLoadedVegetables() {
        return new ArrayList<>(vegetables.values());
    }
    
    public List<Vegetable> loadAllVegetables() {
        for (int i = 0; i < vegetablesNodes.getLength(); i++) {
            String vegetableName = ((Element)vegetablesNodes.item(i)).getElementsByTagName("name").item(0).getTextContent();
            if (!vegetables.containsKey(vegetableName)) {
                vegetables.put(vegetableName, findVegetable(vegetableName));
            }
        }
        return getLoadedVegetables();
    }
    
    public static void sort(List<Vegetable> vegetables, boolean ascending) {
        quickSort(vegetables, 0, vegetables.size() - 1, ascending);
    }
    
    public static void sort(List<Vegetable> vegetables, Comparator<? super Vegetable> comp, boolean ascending) {
        quickSort(vegetables, comp, 0, vegetables.size() - 1, ascending);
    }
    
    public static List<Vegetable> getVegetablesByCaloricity(List<Vegetable> vegetables, double from, double to) {
        List<Vegetable> veg = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            if (vegetable.getCaloricity() >= from && vegetable.getCaloricity() <= to) {
                veg.add(vegetable);
            }
        }
        return veg;
    }
    
    private static void quickSort(List<Vegetable> vegetables, int from, int to, boolean ascending) {
        int startFrom = from;
        int startTo = to;
        Vegetable pivot = vegetables.get((from + to) / 2);
        while (from <= to) {
            if (ascending) {
                while (vegetables.get(from).compareTo(pivot) < 0)
                    from++;
                while (vegetables.get(to).compareTo(pivot) > 0)
                    to--;
                if (from <= to) {
                    Vegetable temp = vegetables.get(from);
                    vegetables.set(from, vegetables.get(to));
                    vegetables.set(to, temp);
                    from++;
                    to--;
                }
            } else {
                while (vegetables.get(from).compareTo(pivot) > 0)
                    from++;
                while (vegetables.get(to).compareTo(pivot) < 0)
                    to--;
                if (from <= to) {
                    Vegetable temp = vegetables.get(from);
                    vegetables.set(from, vegetables.get(to));
                    vegetables.set(to, temp);
                    from++;
                    to--;
                }    
            }
        }
        if (from - 1 > startFrom)
            quickSort(vegetables, startFrom, from - 1, ascending);
        if (from < startTo)
            quickSort(vegetables, from, startTo, ascending);
    }
    
    private static void quickSort(List<Vegetable> vegetables, Comparator<? super Vegetable> comp, int from, int to,  boolean ascending) {
        int startFrom = from;
        int startTo = to;
        Vegetable pivot = vegetables.get((from + to) / 2);
        while (from <= to) {
            if (ascending) {
                while (comp.compare(vegetables.get(from), pivot) < 0)
                    from++;
                while (comp.compare(vegetables.get(to), pivot) > 0)
                    to--;
                if (from <= to) {
                    Vegetable temp = vegetables.get(from);
                    vegetables.set(from, vegetables.get(to));
                    vegetables.set(to, temp);
                    from++;
                    to--;
                }
            } else {
                while (comp.compare(vegetables.get(from), pivot) > 0)
                    from++;
                while (comp.compare(vegetables.get(to), pivot) < 0)
                    to--;
                if (from <= to) {
                    Vegetable temp = vegetables.get(from);
                    vegetables.set(from, vegetables.get(to));
                    vegetables.set(to, temp);
                    from++;
                    to--;
                }
            }
        }
        if (from - 1 > startFrom)
              quickSort(vegetables, comp, startFrom, from - 1, ascending);
        if (from < startTo)
              quickSort(vegetables, comp, from, startTo, ascending);
    }
    
    private Vegetable findVegetable(String name) {
        Element vegElement = null;
        for (int i = 0; i < vegetablesNodes.getLength(); i++) {
            Node node = ((Element)vegetablesNodes.item(i)).getElementsByTagName("name").item(0);
            if (node.getTextContent().equals(name)) {
                vegElement = (Element) vegetablesNodes.item(i);
            }
        }
        if (vegElement == null) {
            IllegalArgumentException exception = new IllegalArgumentException("Vegetable with name <" + name + "> not found");
            log.error("Error in method findVegetable: ", exception);
            throw exception;
        }
        Vegetable vegetable = new VegetableBuilder(vegElement.getElementsByTagName("name").item(0).getTextContent(),
                                                   getDoubleByTagName(vegElement, "caloricityPerGramm"))
                .setProteinsPerGramm(getDoubleByTagName(vegElement, "proteinsPerGramm"))
                .setFatPerGramm(getDoubleByTagName(vegElement, "fatPerGramm"))
                .setCarbohydratesPerGramm(getDoubleByTagName(vegElement, "carbohydratesPerGramm"))
                .build();
        vegetables.put(name, vegetable);
        return vegetable;
    }

    private double getDoubleByTagName(Element vegElement, String tagName) {
        return Double.parseDouble(vegElement.getElementsByTagName(tagName).item(0).getTextContent());
        
    }

    
    @Override
    public String toString() {
        return "VegetableManager [vegetables=" + vegetables + ", vegetablesNodes=" + vegetablesNodes + "]";
    }

}
