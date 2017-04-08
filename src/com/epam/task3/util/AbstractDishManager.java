package com.epam.task3.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.epam.task3.entity.Dish;

public abstract class AbstractDishManager implements DishManager{    
    private ProductManager productManager;
    protected HashMap<String, Dish> dishes;
        
    public AbstractDishManager(ProductManager productManager) {
        super();
        this.productManager = productManager;
        dishes = new HashMap<>();
    }
        
    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public Dish getDish(String name) {
        Dish dish = dishes.get(name);
        if (dish == null) {
            dish = makeDish(name);
            dishes.put(name, dish);
        }
        return dish;
    }
    
    @Override
    public List<Dish> getLoadedDishes() {
        return new ArrayList<>(dishes.values());
    }
    
    @Override
    public List<Dish> getDishes(Set<String> dishesNames) {
        List<Dish> dishesList = new ArrayList<>();
        Dish dish;
        for (String dishName : dishesNames) {
            if (!dishes.containsKey(dishName)) {
                dish = makeDish(dishName);
                dishes.put(dishName, dish);
            } else {
                dish = dishes.get(dishName);
            }
            dishesList.add(dish);
        }
        return dishesList;
    }
        
    protected abstract Dish makeDish(String dishName);
    
    @Override
    public String toString() {
        return "SaladManager [vegetableManager=" + productManager + ", salads=" + dishes + "]";
    }
    
}
