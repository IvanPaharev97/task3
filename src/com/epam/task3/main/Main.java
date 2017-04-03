package com.epam.task3.main;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import com.epam.task3.salad.Salad;
import com.epam.task3.util.ManagerFactory;
import com.epam.task3.util.SaladManager;
import com.epam.task3.util.VegetableManager;
import com.epam.task3.vegetable.Vegetable;

public class Main {

    public static void main(String[] args) {
        File chefData = new File("resources/chefData.xml");
        VegetableManager vegetableManager = ManagerFactory.getVegetableManager(chefData);
        SaladManager saladManager = ManagerFactory.getSaladManager(chefData);
        System.out.println(vegetableManager.getVegetable("Potato"));
        Salad salad = saladManager.getSalad("Vitaminic");
        System.out.println(salad.getCaloricity());
        vegetableManager.loadAllVegetables();
        List<Vegetable> vegetables = vegetableManager.getLoadedVegetables();
        VegetableManager.sort(vegetables, true);
        VegetableManager.sort(vegetables, new Comparator<Vegetable>() {
            @Override
            public int compare(Vegetable arg0, Vegetable arg1) {
                if (arg0.getName().compareTo(arg1.getName()) > 0) {
                    return 1;
                }
                if (arg0.getName().compareTo(arg1.getName()) < 0) {
                    return -1;
                }
                return 0;
            }
        }, false);
        System.out.println(saladManager.getSalad("Freshy"));
        System.out.println(vegetables);
        System.out.println(VegetableManager.getVegetablesByCaloricity(vegetables, 0.6, 0.7));
        System.out.println(SaladManager.getSaladsByCaloricity(saladManager.getLoadedSalads(), 300, 1000));
    }
    
}
