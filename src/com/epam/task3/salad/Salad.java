package com.epam.task3.salad;

import java.util.HashMap;

import com.epam.task3.vegetable.Vegetable;

public class Salad implements Comparable<Salad>{
    private String name;
    private double caloricity;
    private double weight;
    private HashMap<Vegetable, Double> vegetablesWeights;
    
    public Salad(String name, double caloricity, double weight,
                 HashMap<Vegetable, Double> vegetablesWeights) {
        super();
        this.name = name;
        this.caloricity = caloricity;
        this.weight = weight;
        this.vegetablesWeights = vegetablesWeights;
    }

    public String getName() {
        return name;
    }

    public double getCaloricity() {
        return caloricity;
    }      
    
    public double getWeight() {
        return weight;
    }

    public HashMap<Vegetable, Double> getVegetables() {
        return vegetablesWeights;
    }

    @Override
    public String toString() {
        return "Salad [name=" + name + ", caloricity=" + caloricity + ", weight="
                + weight + ", vegetablesWeights=" + vegetablesWeights + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(caloricity);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((vegetablesWeights == null) ? 0 : vegetablesWeights.hashCode());
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Salad other = (Salad) obj;
        if (Double.doubleToLongBits(caloricity) != Double.doubleToLongBits(other.caloricity))
            return false;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (vegetablesWeights == null) {
            if (other.vegetablesWeights != null)
                return false;
        } else if (!vegetablesWeights.equals(other.vegetablesWeights))
            return false;
        return true;
    }

    
    @Override
    public int compareTo(Salad otherSalad) {
        if (caloricity > otherSalad.caloricity) {
            return 1;
        }
        if (caloricity < otherSalad.caloricity) {
            return -1;
        }
        return 0;
    }
    
}
