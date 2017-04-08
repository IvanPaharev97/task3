package com.epam.task3.entity;

import java.util.HashMap;

public class Salad extends Dish{
    private HashMap<Product, Double> vegetablesWeights;
    
    public Salad(String name, double caloricity, double weight,
                 HashMap<Product, Double> vegetablesWeights) {
        super(name, caloricity, weight);
        this.vegetablesWeights = vegetablesWeights;
    }

    public HashMap<Product, Double> getVegetables() {
        return vegetablesWeights;
    }

    @Override
    public String toString() {
        return "Salad [name=" + getName() + ", caloricity=" + getCaloricity() + ", weight="
                + super.getWeight() + ", vegetablesWeights=" + vegetablesWeights + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((vegetablesWeights == null) ? 0 : vegetablesWeights.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Salad other = (Salad) obj;
        if (vegetablesWeights == null) {
            if (other.vegetablesWeights != null)
                return false;
        } else if (!vegetablesWeights.equals(other.vegetablesWeights))
            return false;
        return true;
    }



    
}
