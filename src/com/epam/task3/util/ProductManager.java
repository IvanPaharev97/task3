package com.epam.task3.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


import com.epam.task3.entity.Product;

public interface ProductManager {
    
    public Product getProduct(String name);
    public List<Product> getProducts(Set<String> productsNames);    
    public List<Product> getLoadedProducts();
    public List<Product> loadAllProducts();    
    
    public static void sort(List<Product> products, Comparator<? super Product> comp, boolean ascending) {
        sortFromTo(products, comp, 0, products.size() - 1, ascending);
    }
    
    public static List<? super Product> getProductsByCaloricity(List<Product> products, double from, double to) {
        List<Product> prod = new ArrayList<>();
        for (Product product : products) {
            if (product.getCaloriesPerGramm() >= from && product.getCaloriesPerGramm() <= to) {
                prod.add(product);
            }
        }
        return prod;
    }
    
    public static void sortFromTo(List<Product> products, Comparator<? super Product> comp, int from, int to,  boolean ascending) {
        int startFrom = from;
        int startTo = to;
        Product pivot = products.get((from + to) / 2);
        while (from <= to) {
            if (ascending) {
                while (comp.compare(products.get(from), pivot) < 0)
                    from++;
                while (comp.compare(products.get(to), pivot) > 0)
                    to--;
                if (from <= to) {
                    Product temp = products.get(from);
                    products.set(from, products.get(to));
                    products.set(to, temp);
                    from++;
                    to--;
                }
            } else {
                while (comp.compare(products.get(from), pivot) > 0)
                    from++;
                while (comp.compare(products.get(to), pivot) < 0)
                    to--;
                if (from <= to) {
                    Product temp = products.get(from);
                    products.set(from, products.get(to));
                    products.set(to, temp);
                    from++;
                    to--;
                }
            }
        }
        if (from - 1 > startFrom)
              sortFromTo(products, comp, startFrom, from - 1, ascending);
        if (from < startTo)
              sortFromTo(products, comp, from, startTo, ascending);
    }

    
}
