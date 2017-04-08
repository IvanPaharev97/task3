package com.epam.task3.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.epam.task3.entity.Product;
public abstract class AbstractProductManager implements ProductManager{
    protected HashMap<String, Product> products;
    
    public AbstractProductManager() {
        products = new HashMap<>();
    }
    
    @Override
    public Product getProduct(String name) {
        Product product = products.get(name);
        if (product == null) {
            product = findProduct(name);
            products.put(name, product);
        }
        return product;
    }

    @Override
    public List<Product> getProducts(Set<String> productsNames) {
        List<Product> productList = new ArrayList<>();
        Product product;
        for (String productName : productsNames) {
            if (!products.containsKey(productName)) {
                product = findProduct(productName);
                products.put(productName, product);
            } else {
                product = products.get(productName);
            }
            productList.add(product);
        }
        return productList;
    }
    
    @Override
    public List<Product> getLoadedProducts() {
        return new ArrayList<>(products.values());
    }
     
    protected abstract Product findProduct(String name);
    
    @Override
    public String toString() {
        return "VegetableManager [vegetables=" + products + "]";
    }

}
