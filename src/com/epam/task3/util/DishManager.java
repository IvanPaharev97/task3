package com.epam.task3.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.epam.task3.entity.Dish;

public interface DishManager {
    
    public Dish getDish(String name);    
    public List<Dish> getLoadedDishes();    
    public List<Dish> getDishes(Set<String> dishesNames);    
    public List<Dish> loadAllDishes();
    
    public static void sort(List<Dish> dishes, Comparator<? super Dish> comp, boolean ascending) {
        sortFromTo(dishes, comp, 0, dishes.size() - 1, ascending);
    }
    
    public static List<Dish> getSaladsByCaloricity(List<Dish> dishes, double from, double to) {
        List<Dish> foundDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCaloricity() >= from && dish.getCaloricity() <= to) {
                foundDishes.add(dish);
            }
        }
        return foundDishes;
    }
        
    public static void sortFromTo(List<Dish> dishes, Comparator<? super Dish> comp, int from, int to,  boolean ascending) {
        int startFrom = from;
        int startTo = to;
        Dish pivot = dishes.get((from + to) / 2);
        while (from <= to) {
            if (ascending) {
                while (comp.compare(dishes.get(from), pivot) < 0)
                    from++;
                while (comp.compare(dishes.get(to), pivot) > 0)
                    to--;
                if (from <= to) {
                    Dish temp = dishes.get(from);
                    dishes.set(from, dishes.get(to));
                    dishes.set(to, temp);
                    from++;
                    to--;
                }
            } else {
                while (comp.compare(dishes.get(from), pivot) > 0)
                    from++;
                while (comp.compare(dishes.get(to), pivot) < 0)
                    to--;
                if (from <= to) {
                    Dish temp = dishes.get(from);
                    dishes.set(from, dishes.get(to));
                    dishes.set(to, temp);
                    from++;
                    to--;
                }
            }
        }
        if (from - 1 > startFrom)
              sortFromTo(dishes, comp, startFrom, from - 1, ascending);
        if (from < startTo)
              sortFromTo(dishes, comp, from, startTo, ascending);
    }
        
}
